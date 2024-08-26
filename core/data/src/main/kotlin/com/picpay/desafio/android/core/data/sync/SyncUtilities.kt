package com.picpay.desafio.android.core.data.sync

import com.picpay.desafio.android.core.network.model.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

interface Synchronizer {

    suspend fun getStoredUsersResponseHash(): String?

    suspend fun saveUsersResponseHash(hash: String): Boolean

    suspend fun hashUserResponseList(users: List<UserResponse>): String

    suspend fun Syncable.sync() = this@sync.syncWith(this@Synchronizer)

}

interface Syncable {

    suspend fun syncWith(synchronizer: Synchronizer): Boolean

}

suspend fun Synchronizer.usersSync(
    ioDispatcher: CoroutineDispatcher,
    usersFetcher: suspend () -> List<UserResponse>?,
    usersPersistence: suspend (List<UserResponse>) -> Boolean
): Boolean =
    withContext(ioDispatcher) {
        runCatching {
            usersFetcher()?.let { usersResponse ->

                val fetchedUsersHash = hashUserResponseList(usersResponse)

                val dataIsUpToDate = getStoredUsersResponseHash() == fetchedUsersHash
                if (dataIsUpToDate) return@runCatching true

                val dataIsPersisted = awaitAll(
                    async { usersPersistence(usersResponse) }
                ).all { it }

                if (dataIsPersisted) saveUsersResponseHash(fetchedUsersHash) else false
            } ?: false
        }.getOrElse { exception ->
            exception.printStackTrace()
            false
        }
    }
