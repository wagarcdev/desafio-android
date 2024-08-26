package com.picpay.desafio.android.core.domain.di

import com.picpay.desafio.android.core.domain.usecase.LocalUsersFlowUseCase
import com.picpay.desafio.android.core.domain.usecase.SearchLocalUsersFlowUseCase
import org.koin.dsl.module

val domainModule = module {

    single { LocalUsersFlowUseCase(get()) }

    single { SearchLocalUsersFlowUseCase(get()) }

}