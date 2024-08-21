package com.picpay.desafio.android.contacts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    SearchBar(
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

    val listState = rememberLazyListState()
    LaunchedEffect(
        searchUiState.searchQuery,
        searchUiState.sortedBy,
        searchUiState.orderingDirection,
    ) { listState.scrollToItem(0) }
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemsIndexed(
            items = uiState.users,
            key = { _, it -> it.externalId }
        ) { index, user ->

            ContactItem(user, searchString)

            if (index == uiState.users.size - 1) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
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