package com.picpay.desafio.android.core.data.sync

import com.picpay.desafio.android.datastore.PreferencesDataSource
import com.picpay.desafio.android.network.model.UserResponse

class TestSynchronizer(
    private val preferences: PreferencesDataSource
): Synchronizer {
    override suspend fun getStoredUsersResponseHash(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun saveUsersResponseHash(hash: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun hashUserResponseList(users: List<UserResponse>): String {
        TODO("Not yet implemented")
    }
}