package com.picpay.desafio.android.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.contacts.datasource.usecase.LocalUsersFlowUseCase
import com.picpay.desafio.android.data.SyncManager
import com.picpay.desafio.android.network.NetworkMonitor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ContactsScreenViewModel(
    syncManager: SyncManager,
    localUsersFlowUseCase: LocalUsersFlowUseCase,
    networkMonitor: NetworkMonitor
) : ViewModel() {

    val uiState = combine(
        networkMonitor.isOnline,
        localUsersFlowUseCase.invoke(),
        syncManager.isSyncing
    ) { isOnline, localUsers, isSyncing ->
        ContactsScreenUiState(
            isNetworkAvailable = isOnline,
            users = localUsers,
            isSyncing = isSyncing
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        ContactsScreenUiState()
    )
}