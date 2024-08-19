package com.picpay.desafio.android.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.contacts.datasource.usecase.LocalUsersFlowUseCase
import com.picpay.desafio.android.contacts.datasource.usecase.SyncUsersUseCase
import com.picpay.desafio.android.domain.model.UserModel
import com.picpay.desafio.android.network.NetworkMonitor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsScreenViewModel(
    private val syncUsersUseCase: SyncUsersUseCase,
    private val localUsersFlowUseCase: LocalUsersFlowUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    var uiState = MutableStateFlow(ContactsScreenUiState())
        private set

    init {
        setLoadingTo(true)
        watchLocalUsers()
        watchNetworkStatus()
        syncUsers()
    }

    private fun watchNetworkStatus() {
        viewModelScope.launch {
            networkMonitor.networkStatus.collect { netStatus ->
                uiState.update {
                    it.copy(
                        isNetworkAvailable = netStatus
                    )
                }
                syncIfNeeded()
            }
        }
    }

    private fun watchLocalUsers() {
        viewModelScope.launch {
            localUsersFlowUseCase.invoke().distinctUntilChanged().collect { users ->
                uiState.update {
                    it.copy(
                        users = users
                    )
                }
                if (uiState.value.users.isNotEmpty()) setLoadingTo(false)
            }
        }
    }

    private fun syncIfNeeded() {
        if (uiState.value.syncFailed && uiState.value.isNetworkAvailable) {
            syncUsers()
        }
    }

    fun syncUsers() {
        if (uiState.value.users.isEmpty()) {
            setSyncFailedStatusTo(false)
            setLoadingTo(true)
        }
        viewModelScope.launch {
            if (!uiState.value.isNetworkAvailable) {
                delay(300)
                setDisplayMessage("              Sem internet\nNão foi possível sincronizar...")
                setSyncFailedStatusTo(true)
                setLoadingTo(false)
                delay(500)
                setDisplayMessage(null)
                return@launch
            }

            setDisplayMessage("Sincronizando...")

            when (val response: ApiResponse<List<UserModel>> = syncUsersUseCase()) {
                is ApiResponse.Error<List<UserModel>> -> {
                    setSyncFailedStatusTo(true)
                    setLoadingTo(false)
                    setDisplayMessage("Falha ao sincronizar...\nErro: ${response.code} | ${response.error?.message}")
                    delay(500)
                    setDisplayMessage("Tentando sicronizar novamente...")
                    syncUsers()
                }

                is ApiResponse.Success<List<UserModel>> -> {
                    setLoadingTo(false)
                    setSyncFailedStatusTo(false)
                    delay(300)
                    setDisplayMessage("Contatos estão atualizados!")
                }
            }
        }
    }

    private fun setLoadingTo(condition: Boolean) {
        uiState.update {
            it.copy(
                isLoading = condition
            )
        }
    }

    private fun setDisplayMessage(message: String?) {
        uiState.update {
            it.copy(
                displayMessage = message
            )
        }
    }

    private fun setSyncFailedStatusTo(condition: Boolean) {
        uiState.update {
            it.copy(
                syncFailed = condition
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        networkMonitor.cleanup()
    }
}
