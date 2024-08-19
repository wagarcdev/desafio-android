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

    override suspend fun syncLocalUsersWithRemote(): ApiResponse<List<UserModel>> {
        when (val usersApiResponse = getRemoteUsers()) {

            is ApiResponse.Error -> return ApiResponse.Error()

            is ApiResponse.Success -> {
                insertLocalUsers(*usersApiResponse.value.toTypedArray())
                return ApiResponse.Success(emptyList()) //no need to return data
            }
        }
    }

    override fun getLocalUsers(): Flow<List<UserModel>>  =
        localDataSource.getUsers()

    override suspend fun insertLocalUser(user: UserModel) =
        localDataSource.insertUser(user)

    override suspend fun getRemoteUsers() =
        remoteDataSource.getUsers()

    override suspend fun insertLocalUsers(vararg users: UserModel) =
        localDataSource.insertUsers(*users)

}