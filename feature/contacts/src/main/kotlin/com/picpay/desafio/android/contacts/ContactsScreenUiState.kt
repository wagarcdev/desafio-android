package com.picpay.desafio.android.contacts

import com.picpay.desafio.android.domain.model.UserModel

data class ContactsScreenUiState(
    var isLoading: Boolean = false,
    var isNetworkAvailable: Boolean = false,
    var displayMessage: String? = null,
    var syncFailed: Boolean = false,
    var users: List<UserModel> = emptyList(),
){
    val showRetry: Boolean
        get() = syncFailed && users.isEmpty()

    val showNoInternet: Boolean
        get() = showRetry && !isNetworkAvailable
}
