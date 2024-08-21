package com.picpay.desafio.android.contacts.datasource.repository

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun getUsers(): ApiResponse<List<UserModel>>
}

interface UserLocalDataSource {

    fun searchUser(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>>

    fun getUsers(): Flow<List<UserModel>>

    suspend fun insertUser(user: UserModel)

    suspend fun insertUsers(vararg user: UserModel)
}