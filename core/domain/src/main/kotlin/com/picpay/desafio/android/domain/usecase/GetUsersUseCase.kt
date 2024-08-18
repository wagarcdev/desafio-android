package com.picpay.desafio.android.domain.usecase

import com.picpay.desafio.android.domain.repository.UsersRepository

class GetUsersUseCase(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke() = usersRepository.getContacts()
}

