package com.picpay.desafio.android.contacts.datasource.usecase

import com.picpay.desafio.android.contacts.datasource.repository.UsersRepository

class GetRemoteUsersUseCase(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke() = usersRepository.getRemoteUsers()
}