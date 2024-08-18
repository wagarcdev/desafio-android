package com.picpay.desafio.android.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.common.util.ApiResponse
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactsScreenViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    var uiState = MutableStateFlow(ContactsScreenUiState())
        private set

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            setLoadingTo(true)
            setError(null)
            when(val apiResponse = getUsersUseCase()) {
                is ApiResponse.Success -> updateUserList(apiResponse.value)
                is ApiResponse.Error -> setError("Error: ${apiResponse.code} | ${apiResponse.error?.message}")
            }
            setLoadingTo(false)
        }
    }

    private fun setLoadingTo(condition: Boolean) {
        uiState.update {
            it.copy(
                isLoading = condition
            )
        }
    }

    private fun setError(message: String?) {
        uiState.update {
            it.copy(
                isError = message
            )
        }
    }

    private fun updateUserList(users: List<User>?) {
        if (users != null) {
            uiState.update {
                it.copy(
                    users = users
                )
            }
        }
    }
}
