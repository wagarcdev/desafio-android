package com.picpay.desafio.android.contacts.components.searchbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.picpay.desafio.android.contacts.components.searchbar.sortmenu.OrderDirectionMenu
import com.picpay.desafio.android.contacts.components.searchbar.sortmenu.SortByMenu
import com.picpay.desafio.android.contacts.viewmodel.ContactUiEvent
import com.picpay.desafio.android.contacts.viewmodel.SearchUiState

@Composable
fun ContactsSearchBar(
    searchUiState: SearchUiState,
    search: String,
    onEvent: (ContactUiEvent) -> Unit
) {
    val isSearchFieldFocused = remember { mutableStateOf(false) }

    val isSortByMenuExpanded = remember { mutableStateOf(false) }
    val isOrderDirectionMenuExpanded = remember { mutableStateOf(false) }

    val menuWeight = 0.15f
    val spacerWeigh = 0.025f

    Row(Modifier.fillMaxWidth()) {
        ContactsSearchField(
            modifier = Modifier.weight(0.6f),
            isSearchFieldFocused = isSearchFieldFocused,
            search = search,
            onEvent = onEvent,
        )

        Spacer(modifier = Modifier.weight(spacerWeigh))

        OrderDirectionMenu(
            modifier = Modifier.weight(menuWeight),
            isExpanded = isOrderDirectionMenuExpanded,
            searchUiState = searchUiState,
            onEvent = onEvent
        )

        Spacer(modifier = Modifier.weight(spacerWeigh))

        SortByMenu(
            modifier = Modifier.weight(menuWeight),
            isExpanded = isSortByMenuExpanded,
            searchUiState = searchUiState,
            onEvent = onEvent
        )
    }
}


@Preview
@Composable
private fun SearchBarPreview() {
    ContactsSearchBar(
        searchUiState = SearchUiState(),
        search = "",
        onEvent = {}
    )
}