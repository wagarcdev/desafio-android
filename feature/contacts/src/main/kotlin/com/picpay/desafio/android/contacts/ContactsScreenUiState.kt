package com.picpay.desafio.android.contacts

import com.picpay.desafio.android.domain.model.User

data class ContactsScreenUiState(
    var isLoading: Boolean = false,
    var isError: String? = null,
    var users: List<User> = emptyList()
)
