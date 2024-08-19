package com.picpay.desafio.android.contacts.datasource.usecase.di

import com.picpay.desafio.android.contacts.datasource.usecase.GetRemoteUsersUseCase
import com.picpay.desafio.android.contacts.datasource.usecase.LocalUsersFlowUseCase
import com.picpay.desafio.android.contacts.datasource.usecase.SyncUsersUseCase
import org.koin.dsl.module

val featureContactsUseCasesModule = module {
    single { GetRemoteUsersUseCase(get()) }

    single { LocalUsersFlowUseCase(get()) }

    single { SyncUsersUseCase(get()) }
}