package com.picpay.desafio.android.core.data.sync

import com.picpay.desafio.android.datastore.PreferencesDataSource
import com.picpay.desafio.android.network.model.UserResponse

class TestSynchronizer(
    private val preferences: PreferencesDataSource,
    private val dataSyncManager: DataSyncManager
): Synchronizer {

    override suspend fun getStoredUsersResponseHash(): String? =
        preferences.getSyncHash()

    override suspend fun saveUsersResponseHash(hash: String): Boolean =
        preferences.saveSyncHash(hash)

    override suspend fun hashUserResponseList(users: List<UserResponse>): String =
        dataSyncManager.hashUserListWithMd5(users)
}