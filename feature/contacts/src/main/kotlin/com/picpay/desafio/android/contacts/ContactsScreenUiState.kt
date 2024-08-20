package com.picpay.desafio.android.contacts

import com.picpay.desafio.android.domain.model.UserModel

data class ContactsScreenUiState(
    var isSyncing: Boolean = false,
    var isNetworkAvailable: Boolean? = null,
    var displayMessage: String? = null,
    var users: List<UserModel> = emptyList(),
){
    val showNoInternet: Boolean
        get() = users.isEmpty() && isNetworkAvailable?.not() ?: false
}