package com.picpay.desafio.android.contacts

import com.picpay.desafio.android.domain.model.UserModel

data class ContactsScreenUiState(
    var isLoading: Boolean = false,
    var isError: String? = null,
    var users: List<UserModel> = emptyList()
)
