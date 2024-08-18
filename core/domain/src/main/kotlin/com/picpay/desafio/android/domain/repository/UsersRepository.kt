package com.picpay.desafio.android.domain.repository

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.domain.model.UserModel

interface UsersRepository {

    suspend fun getContacts(): ApiResponse<List<UserModel>>

}