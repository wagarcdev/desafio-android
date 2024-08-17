package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.repository.UsersRepository

class UsersRepositoryImpl(
    private val service: PicPayService
): UsersRepository {

    override suspend fun getContacts() = service.getUsers()
}