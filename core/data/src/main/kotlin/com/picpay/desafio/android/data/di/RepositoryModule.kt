package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.UsersRepositoryImpl
import com.picpay.desafio.android.domain.repository.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<UsersRepository>{
        UsersRepositoryImpl(get())
    }

}