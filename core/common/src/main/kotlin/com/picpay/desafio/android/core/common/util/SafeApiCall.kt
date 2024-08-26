package com.picpay.desafio.android.core.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<T>
): ApiResponse<T> {
    return withContext(dispatcher) {
        runCatching { apiCall() }
            .fold(
                onSuccess = { response ->
                    if (response.isSuccessful) {
                        response.body()?.let {
                            ApiResponse.Success(it)
                        } ?: ApiResponse.Error(response.code(), Throwable("Response body is null"))
                    } else {
                        val errorMessage = response.errorBody()?.string() ?: response.message()
                        ApiResponse.Error(response.code(), Throwable(errorMessage))
                    }
                },
                onFailure = { exception ->
                    ApiResponse.Error(exception.hashCode(), exception)
                }
            )
    }
}