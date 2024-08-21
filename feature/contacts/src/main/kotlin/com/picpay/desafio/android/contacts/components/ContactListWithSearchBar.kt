package com.picpay.desafio.android.contacts.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.picpay.desafio.android.contacts.components.contactlist.ContactsList
import com.picpay.desafio.android.contacts.components.searchbar.ContactsSearchBar
import com.picpay.desafio.android.contacts.viewmodel.ContactUiEvent
import com.picpay.desafio.android.contacts.viewmodel.ContactsScreenUiState
import com.picpay.desafio.android.contacts.viewmodel.EventSearchChange
import com.picpay.desafio.android.contacts.viewmodel.SearchUiState

@Composable
fun ColumnScope.ContactListWithSearchBar(
    uiState: ContactsScreenUiState,
    searchUiState: SearchUiState,
    onEvent: (ContactUiEvent) -> Unit
) {
    var searchString by remember { mutableStateOf("") }

    ContactsSearchBar(
        search = searchString,
        searchUiState = searchUiState,
        onEvent = { event ->
            when (event) {
                is EventSearchChange -> {
                    searchString = event.search
                    onEvent(event)
                }
                else -> onEvent(event)
            }
        }
    )

    ContactsList(
        searchUiState = searchUiState,
        uiState = uiState,
        searchString = searchString
    )
}

@Preview
@Composable
private fun ContactListWithSearchBarPreview() {
    Column {
        ContactListWithSearchBar(
            uiState = ContactsScreenUiState(),
            searchUiState = SearchUiState(),
            onEvent = { }
        )
    }
}