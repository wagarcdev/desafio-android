package com.picpay.desafio.android.core.domain.usecase

import com.picpay.desafio.android.core.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface SearchLocalUsersFlowUseCase {
    operator fun invoke(
        searchQuery: String,
        sortColumn: String,
        sortOrder: String
    ): Flow<List<UserModel>>

}
