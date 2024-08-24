package com.picpay.desafio.android.contacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.contacts.datasource.usecase.SearchLocalUsersFlowUseCase
import com.picpay.desafio.android.database.sync.SyncManager
import com.picpay.desafio.android.network.NetworkMonitor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalCoroutinesApi::class)
class ContactsScreenViewModel(
    syncManager: SyncManager,
    searchLocalUsersFlowUseCase: SearchLocalUsersFlowUseCase,
    networkMonitor: NetworkMonitor
) : ViewModel() {

    private var searchUiState = MutableStateFlow(SearchUiState())

    private val usersFilteredBySearch = searchUiState.flatMapLatest { state ->
        searchLocalUsersFlowUseCase(
            searchQuery = state.searchQuery,
            sortColumn = state.sortedBy.parameter,
            sortOrder = state.orderDirection.parameter
        )
    }

    val uiState = combine(
        usersFilteredBySearch,
        networkMonitor.isOnline,
        syncManager.isSyncing,
        searchUiState
    ) { usersFiltered,
        isOnline,
        isSyncing,
        searchUiState ->
        ContactsScreenUiState(
            isNetworkAvailable = isOnline,
            users = usersFiltered,
            isSyncing = isSyncing,
            searchUiState = searchUiState
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ContactsScreenUiState()
    )

    fun onEvent(event: ContactUiEvent) {
        when(event) {
            is EventOrderChange -> searchUiState
                .update { it.copy(orderDirection = event.orderDirection) }

            is EventSortChange -> searchUiState
                .update { it.copy(sortedBy = event.sortBy) }

            is EventSearchChange -> searchUiState
                .update { it.copy(searchQuery = event.search.apply { removePrefix("@") }) }
        }
    }
}