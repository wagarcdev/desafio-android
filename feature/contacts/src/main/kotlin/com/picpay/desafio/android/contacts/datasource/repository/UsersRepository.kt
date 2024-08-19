package com.picpay.desafio.android.contacts.datasource.repository

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun syncLocalUsersWithRemote(): ApiResponse<List<UserModel>>

    fun getLocalUsers(): Flow<List<UserModel>>

    suspend fun getRemoteUsers(): ApiResponse<List<UserModel>>

    suspend fun insertLocalUser(user: UserModel)

    suspend fun insertLocalUsers(vararg users: UserModel)



}