package com.picpay.desafio.android.contacts.datasource.repository.impl

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.contacts.datasource.repository.UserLocalDataSource
import com.picpay.desafio.android.contacts.datasource.repository.UserRemoteDataSource
import com.picpay.desafio.android.contacts.datasource.repository.UsersRepository
import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

class UsersRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
): UsersRepository {

    override fun searchUser(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>> =
        localDataSource.searchUser(
            searchQuery = searchQuery,
            sortColumn = sortColumn,
            sortOrder = sortOrder
        )

    override fun getLocalUsers(): Flow<List<UserModel>>  =
        localDataSource.getUsers()

    override suspend fun insertLocalUser(user: UserModel) =
        localDataSource.insertUser(user)

    override suspend fun getRemoteUsers() =
        remoteDataSource.getUsers()

    override suspend fun insertLocalUsers(vararg users: UserModel) =
        localDataSource.insertUsers(*users)

    override suspend fun sync(): Boolean {
        when (val usersApiResponse = getRemoteUsers()) {

            is ApiResponse.Error -> return false

            is ApiResponse.Success -> {
                insertLocalUsers(*usersApiResponse.value.toTypedArray())
                return true
            }
        }
    }

}