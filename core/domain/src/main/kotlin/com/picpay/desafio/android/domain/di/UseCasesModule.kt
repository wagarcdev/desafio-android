package com.picpay.desafio.android.domain.di

import com.picpay.desafio.android.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

val useCasesModule = module {

    factory { GetUsersUseCase(get()) }

}