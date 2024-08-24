package com.picpay.desafio.android.core.data.repository

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.core.data.model.UserModel
import com.picpay.desafio.android.core.data.sync.Syncable
import com.picpay.desafio.android.network.model.UserResponse
import kotlinx.coroutines.flow.Flow

interface UsersRepository: Syncable {

    fun searchUser(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>>

    fun getLocalUsers(): Flow<List<UserModel>>

    suspend fun getRemoteUsers(): ApiResponse<List<UserResponse>>

    suspend fun insertLocalUser(user: UserResponse)

    suspend fun insertLocalUsers(vararg users: UserResponse): Boolean

}