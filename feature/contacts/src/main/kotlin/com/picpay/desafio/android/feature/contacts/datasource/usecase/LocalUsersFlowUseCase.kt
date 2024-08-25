package com.picpay.desafio.android.feature.contacts.datasource.usecase

import com.picpay.desafio.android.core.data.repository.UsersRepository

class LocalUsersFlowUseCase(
    private val usersRepository: UsersRepository
) {

    operator fun invoke() = usersRepository.getLocalUsers()
}