package com.picpay.desafio.android.contacts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.contacts.components.ContactsScreenTitle
import com.picpay.desafio.android.contacts.components.contactlist.ContactsList
import com.picpay.desafio.android.contacts.components.searchbar.ContactsSearchBar
import com.picpay.desafio.android.contacts.viewmodel.ContactUiEvent
import com.picpay.desafio.android.contacts.viewmodel.ContactsScreenUiState
import com.picpay.desafio.android.contacts.viewmodel.ContactsScreenViewModel
import com.picpay.desafio.android.contacts.viewmodel.EventSearchChange
import com.picpay.desafio.android.contacts.viewmodel.IsLoading
import com.picpay.desafio.android.contacts.viewmodel.NoInternet
import com.picpay.desafio.android.contacts.viewmodel.NoResultsOnSearch
import com.picpay.desafio.android.contacts.viewmodel.ShowContactList
import com.picpay.desafio.android.feature.contacts.R
import com.picpay.desafio.android.ui.util.showShortToast
import com.picpay.desafio.android.ui.widgets.ProgressIndicator
import com.picpay.desafio.android.ui.widgets.alerts.NoInternetAlert
import com.picpay.desafio.android.ui.widgets.alerts.NoResultsOnSearchAlert
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactScreen() {

    val viewModel: ContactsScreenViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    ContactsScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun ContactsScreenContent(
    uiState: ContactsScreenUiState,
    onEvent: (ContactUiEvent) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(uiState.isSyncing) {
        if (uiState.isSyncing) showShortToast(R.string.synchronizing, context)
    }

    Column(Modifier.fillMaxSize().padding(horizontal = 24.dp)) {

        ContactsScreenTitle()

        var searchString by remember { mutableStateOf("") }
        ContactsSearchBar(
            search = searchString,
            searchUiState = uiState.searchUiState,
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

        when (uiState.condition) {
            NoInternet -> NoInternetAlert()
            IsLoading -> ProgressIndicator(Modifier.fillMaxSize())
            NoResultsOnSearch -> NoResultsOnSearchAlert()
            ShowContactList -> {
                ContactsList(
                    uiState = uiState,
                    searchString = searchString
                )
            }
        }
    }
}

@Preview
@Composable
private fun ContactsScreenContentPreview() {
    ContactsScreenContent(
        uiState = ContactsScreenUiState(),
        onEvent = { }
    )
}