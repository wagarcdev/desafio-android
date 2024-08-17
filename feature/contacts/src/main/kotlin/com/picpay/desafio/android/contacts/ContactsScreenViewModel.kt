package com.picpay.desafio.android.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.domain.repository.UsersRepository
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

    private fun fetchUsers() {
        viewModelScope.launch {
            setError(null)
            setLoadingTo(true)
            try {
                val users = getUsersUseCase()
                setLoadingTo(false)
                if (users != null) {
                    updateUserList(users)
                } else {
                    setError("Error: failed to fetch users")
                }
            } catch (e: Exception) {
                setLoadingTo(false)
                setError("Failed to load users")
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
