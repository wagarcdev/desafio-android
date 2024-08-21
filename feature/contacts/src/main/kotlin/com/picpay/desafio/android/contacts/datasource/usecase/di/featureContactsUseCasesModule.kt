package com.picpay.desafio.android.contacts.datasource.usecase.di

import com.picpay.desafio.android.contacts.datasource.usecase.LocalUsersFlowUseCase
import com.picpay.desafio.android.contacts.datasource.usecase.SearchLocalUsersFlowUseCase
import org.koin.dsl.module

val featureContactsUseCasesModule = module {

    single { LocalUsersFlowUseCase(get()) }

    single { SearchLocalUsersFlowUseCase(get()) }

}