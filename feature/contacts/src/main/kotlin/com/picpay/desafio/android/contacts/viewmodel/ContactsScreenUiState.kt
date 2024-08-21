package com.picpay.desafio.android.contacts.viewmodel

import com.picpay.desafio.android.domain.model.UserModel

data class ContactsScreenUiState(
    var isSyncing: Boolean = false,
    var isNetworkAvailable: Boolean? = null,
    var displayMessage: String? = null,
    var users: List<UserModel> = emptyList(),
) {
    val showCondition: ShowCondition
        get() =
            if (users.isEmpty() && isNetworkAvailable?.not() == true) {
                NoInternet
            } else {
                if (isSyncing && users.isEmpty()) {
                    IsLoading
                } else {
                    ShowContacts
                }
            }
}

sealed interface ShowCondition
data object NoInternet : ShowCondition
data object IsLoading : ShowCondition
data object ShowContacts : ShowCondition