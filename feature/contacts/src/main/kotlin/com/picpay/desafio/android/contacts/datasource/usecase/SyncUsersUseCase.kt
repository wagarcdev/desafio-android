package com.picpay.desafio.android.contacts.datasource.usecase

import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.contacts.datasource.repository.UsersRepository
import com.picpay.desafio.android.domain.model.UserModel

class SyncUsersUseCase(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(): ApiResponse<List<UserModel>> = usersRepository.syncLocalUsersWithRemote()
}
