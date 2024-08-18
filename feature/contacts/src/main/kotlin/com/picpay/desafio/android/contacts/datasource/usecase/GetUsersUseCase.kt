package com.picpay.desafio.android.contacts.datasource.usecase

import com.picpay.desafio.android.contacts.datasource.repository.UsersRepository

class GetUsersUseCase(
    private val usersRepository: com.picpay.desafio.android.contacts.datasource.repository.UsersRepository
) {

    suspend operator fun invoke() = usersRepository.getContacts()
}

