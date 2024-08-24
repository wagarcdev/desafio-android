package com.picpay.desafio.android.contacts.datasource.local

import com.picpay.desafio.android.core.database.dao.UserDao
import com.picpay.desafio.android.core.database.model.toEntity
import com.picpay.desafio.android.core.database.model.toModel
import com.picpay.desafio.android.core.data.model.UserModel
import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLocalDataSourceImpl(
    private val userDao: UserDao
) : UserLocalDataSource {

    override fun searchUser(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>> =
        userDao.searchUsers(
            searchQuery = searchQuery,
            sortColumn = sortColumn,
            sortOrder = sortOrder
        ).map { users -> users.map { it.toModel() } }

    override fun getUsers(): Flow<List<UserModel>> = userDao.getAllUsers()
        .map { users -> users.map { it.toModel() } }

    override suspend fun insertUser(user: UserModel) = userDao.insertUser(user.toEntity())

    override suspend fun insertUsers(vararg user: UserModel): Boolean =
        runCatching {
            user.forEach { insertUser(it) }
        }.fold(
            onSuccess = { true },
            onFailure = { false }
        )

}

