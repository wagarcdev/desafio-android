package com.picpay.desafio.android.contacts.components.contactlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.contacts.viewmodel.ContactsScreenUiState
import com.picpay.desafio.android.contacts.viewmodel.SearchUiState
import kotlinx.coroutines.delay

@Composable
fun ContactsList(
    searchUiState: SearchUiState,
    uiState: ContactsScreenUiState,
    searchString: String
) {
    val focusManager = LocalFocusManager.current
    val interaction = remember { MutableInteractionSource() }

    val listState = rememberLazyListState()
    LaunchedEffect(
        searchUiState.searchQuery,
        searchUiState.sortedBy,
        searchUiState.orderDirection,
    ) {
        delay(50)
        listState.scrollToItem(0)
    }
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        itemsIndexed(
            items = uiState.users,
            key = { _, it -> it.externalId }
        ) { index, user ->

            ContactListItem(
                modifier = Modifier
                    .clickable(
                        interactionSource = interaction,
                        indication = null
                    ) { focusManager.clearFocus() },
                user = user,
                searchString = searchString
            )

            if (index == uiState.users.size - 1) {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}