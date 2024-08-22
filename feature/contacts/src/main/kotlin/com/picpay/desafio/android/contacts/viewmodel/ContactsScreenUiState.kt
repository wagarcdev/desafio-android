package com.picpay.desafio.android.contacts.viewmodel

import com.picpay.desafio.android.domain.model.UserModel

data class ContactsScreenUiState(
    var searchUiState: SearchUiState = SearchUiState(),
    var isSyncing: Boolean = false,
    var isNetworkAvailable: Boolean? = null,
    var displayMessage: String? = null,
    var users: List<UserModel> = emptyList(),
) {
    val condition: ContactsUiStateCondition
        get() =
            if (users.isEmpty() && isNetworkAvailable?.not() == true) {
                NoInternet
            } else {
                if (isSyncing && users.isEmpty()) {
                    IsLoading
                } else {
                    if (searchUiState.searchQuery.isNotEmpty() && users.isEmpty()) {
                        NoResultsOnSearch
                    } else {
                        ShowContactList
                    }
                }
            }
}

sealed interface ContactsUiStateCondition
data object NoInternet : ContactsUiStateCondition
data object IsLoading : ContactsUiStateCondition
data object ShowContactList : ContactsUiStateCondition
data object NoResultsOnSearch : ContactsUiStateCondition