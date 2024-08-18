package com.picpay.desafio.android.common.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ApiResponse<T> {
    return withContext(dispatcher) {
        try {
            ApiResponse.Success(apiCall.invoke())
        } catch (ex: Exception) {
            ApiResponse.Error(code = ex.hashCode(), error = ex.cause)
        }
    }
}