package com.picpay.desafio.android.database.repository.impl

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.database.model.UserModel
import com.picpay.desafio.android.database.model.mappers.toDomainModel
import com.picpay.desafio.android.database.repository.UserLocalDataSource
import com.picpay.desafio.android.database.repository.UserRemoteDataSource
import com.picpay.desafio.android.database.repository.UsersRepository
import com.picpay.desafio.android.database.sync.Synchronizer
import com.picpay.desafio.android.database.sync.usersSync
import com.picpay.desafio.android.network.model.UserResponse
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

    override suspend fun insertLocalUser(user: UserResponse) =
        localDataSource.insertUser(user.toDomainModel())

    override suspend fun getRemoteUsers() =
        remoteDataSource.getUsers()

    override suspend fun insertLocalUsers(vararg users: UserResponse) =
        localDataSource.insertUsers(*users.map { it.toDomainModel() }.toTypedArray())

    override suspend fun syncWith(synchronizer: Synchronizer): Boolean =
        synchronizer.usersSync(
            usersFetcher = ::provideUsers,
            usersPersistence = ::insertLocalUsers
        )

    private suspend fun provideUsers() =
        when (val usersApiResponse = getRemoteUsers()) {

            is ApiResponse.Error -> null

            is ApiResponse.Success -> usersApiResponse.value
        }

    private suspend fun insertLocalUsers(users: List<UserResponse>) =
        insertLocalUsers(*users.toTypedArray())
}