package com.picpay.desafio.android.contacts.datasource.repository

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.data.Syncable
import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository: Syncable {

//    suspend fun syncLocalUsersWithRemote(): ApiResponse<List<UserModel>>

    fun searchUser(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>>

    fun getLocalUsers(): Flow<List<UserModel>>

    suspend fun getRemoteUsers(): ApiResponse<List<UserModel>>

    suspend fun insertLocalUser(user: UserModel)

    suspend fun insertLocalUsers(vararg users: UserModel)

}