package com.picpay.desafio.android.contacts.datasource.repository

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.domain.model.UserModel

interface UsersRepository {

    suspend fun getContacts(): ApiResponse<List<UserModel>>

}