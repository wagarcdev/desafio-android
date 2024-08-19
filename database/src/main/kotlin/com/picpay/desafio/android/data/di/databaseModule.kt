package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.DesafioDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single { DesafioDatabase.roomDatabaseBuilder(androidApplication()) }

    single { get<DesafioDatabase>().usersDao() }

}