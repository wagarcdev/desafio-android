package com.picpay.desafio.android.contacts.datasource.remote

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.common.util.safeApiCall
import com.picpay.desafio.android.contacts.datasource.repository.UserRemoteDataSource
import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class UserRemoteDataSourceImpl(
    private val userService: UserService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRemoteDataSource {

    override suspend fun getUsers(): ApiResponse<List<UserModel>> =
        safeApiCall(dispatcher) { userService.getUsers() }
            .let { apiResponse ->
                when (apiResponse) {

                    is ApiResponse.Error -> {
                        ApiResponse.Error(apiResponse.code, apiResponse.error)
                    }

                    is ApiResponse.Success -> {
                        ApiResponse.Success(apiResponse.value.map { it.toDomainModel() })
                    }
                }
            }

}