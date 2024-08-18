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
                    Log.e("safeApiCall", "Exception: ${exception.message}", exception)
                    ApiResponse.Error(exception.hashCode(), exception)
                }
            )
    }
}