package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.domain.model.User

interface UsersRepository {

    suspend fun getContacts(): ApiResponse<List<User>>

}