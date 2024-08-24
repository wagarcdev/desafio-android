package com.picpay.desafio.android.contacts.viewmodel

import com.picpay.desafio.android.database.model.UserModel

data class ContactsScreenUiState(
    var searchUiState: SearchUiState = SearchUiState(),
    var isSyncing: Boolean = false,
    var isNetworkAvailable: Boolean? = null,
    var displayMessage: String? = null,
    var users: List<UserModel> = emptyList(),
) {
    private val isOfflineAndUsersIsEmpty =
        users.isEmpty() && isNetworkAvailable?.not() == true

    private val isSyncingButUsersIsEmpty =
        isSyncing && users.isEmpty()

    private val noResultsOnSearch =
        searchUiState.searchQuery.isNotEmpty() && users.isEmpty()

    val condition: ContactsUiStateCondition
        get() =
            if (isOfflineAndUsersIsEmpty) NoInternet
            else
                if (isSyncingButUsersIsEmpty) IsLoading
                else
                    if (noResultsOnSearch) NoResultsOnSearch else ShowContactList
}

sealed interface ContactsUiStateCondition
data object NoInternet : ContactsUiStateCondition
data object IsLoading : ContactsUiStateCondition
data object ShowContactList : ContactsUiStateCondition
data object NoResultsOnSearch : ContactsUiStateCondition