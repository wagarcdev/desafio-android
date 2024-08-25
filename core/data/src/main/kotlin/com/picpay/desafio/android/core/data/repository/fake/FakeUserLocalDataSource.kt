package com.picpay.desafio.android.core.data.repository.fake

import com.picpay.desafio.android.core.data.model.UserModel
import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import com.picpay.desafio.android.core.data.util.OrderDirection
import com.picpay.desafio.android.core.data.util.SortBy
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

class FakeUserLocalDataSource(
    private val prePopulateList: MutableList<UserModel> = mutableListOf()
) : UserLocalDataSource {


    override fun searchUser(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>> = flow {

        val filteredUsers = prePopulateList.filter { user ->
            user.name.contains(searchQuery, ignoreCase = true) ||
                    user.username.contains(searchQuery, ignoreCase = true)
        }

        val comparator: Comparator<UserModel> = when (sortColumn) {
            SortBy.NAME.parameter -> compareBy { it.name }
            SortBy.USERNAME.parameter -> compareBy { it.username }
            else -> compareBy { it.name }
        }

        val sortedUsers = filteredUsers.sortedWith(comparator)
            .takeUnless { sortOrder == OrderDirection.DESCENDING.parameter }
            ?: filteredUsers.sortedWith(comparator.reversed())

        emit(sortedUsers)
    }

    override fun getUsers(): Flow<List<UserModel>> = flow {
        emit(prePopulateList)
    }

    override suspend fun insertUser(user: UserModel) {
        prePopulateList.add(user)
    }

    override suspend fun insertUsers(vararg user: UserModel): Boolean =
        runBlocking {
            return@runBlocking async { prePopulateList.addAll(user) }
                .await()
        }
}