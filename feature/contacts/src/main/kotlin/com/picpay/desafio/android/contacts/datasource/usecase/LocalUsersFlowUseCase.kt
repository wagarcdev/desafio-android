package com.picpay.desafio.android.contacts.datasource.usecase

import com.picpay.desafio.android.database.repository.UsersRepository

class LocalUsersFlowUseCase(
    private val usersRepository: UsersRepository
) {

    operator fun invoke() = usersRepository.getLocalUsers()
}