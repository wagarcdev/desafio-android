package com.picpay.desafio.android.data

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.common.util.safeApiCall
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.repository.UsersRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

class UsersRepositoryImpl(
    private val service: PicPayService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): UsersRepository {

    override suspend fun getContacts() = safeApiCall(dispatcher) { service.getUsers() }

}