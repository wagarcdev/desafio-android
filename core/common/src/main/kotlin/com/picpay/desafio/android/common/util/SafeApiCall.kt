package com.picpay.desafio.android.common.util

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> Response<T>
): ApiResponse<T> {
    return withContext(dispatcher) {
        runCatching { apiCall() }
            .mapCatching { response ->
                if (response.isSuccessful) {
                    ApiResponse.Success(response.body()!!)
                } else {
                    val errorMessage = response.errorBody()?.string() ?: response.message()
                    Log.e("safeApiCall", errorMessage)
                    ApiResponse.Error(response.code(), Throwable(errorMessage))
                }
            }
            .getOrElse { ApiResponse.Error(it.hashCode(), it) }
    }
}