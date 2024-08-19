package com.picpay.desafio.android.contacts.datasource.usecase

import com.picpay.desafio.android.contacts.datasource.repository.UsersRepository

class LocalUsersFlowUseCase(
    private val usersRepository: UsersRepository
) {

    operator fun invoke() = usersRepository.getLocalUsers()
}