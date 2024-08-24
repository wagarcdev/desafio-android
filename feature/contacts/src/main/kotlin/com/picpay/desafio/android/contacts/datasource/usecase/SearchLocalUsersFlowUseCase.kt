package com.picpay.desafio.android.contacts.datasource.usecase

import com.picpay.desafio.android.database.repository.UsersRepository

class SearchLocalUsersFlowUseCase(
    private val usersRepository: UsersRepository
) {
    operator fun invoke(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ) = usersRepository.searchUser(
        searchQuery = searchQuery,
        sortColumn = sortColumn,
        sortOrder = sortOrder
    )
}