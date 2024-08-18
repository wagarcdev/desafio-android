package com.picpay.desafio.android.data

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.common.util.safeApiCall
import com.picpay.desafio.android.data.remote.UserService
import com.picpay.desafio.android.data.remote.toDomainModel
import com.picpay.desafio.android.domain.repository.UsersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UsersRepositoryImpl(
    private val service: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): UsersRepository {

    override suspend fun getContacts() =
        safeApiCall(dispatcher) { service.getUsers() }.let { apiResponse ->
            when(apiResponse) {
                is ApiResponse.Error -> ApiResponse.Error(apiResponse.code, apiResponse.error)
                is ApiResponse.Success -> ApiResponse.Success(apiResponse.value.map { it.toDomainModel() })
            }
        }
}