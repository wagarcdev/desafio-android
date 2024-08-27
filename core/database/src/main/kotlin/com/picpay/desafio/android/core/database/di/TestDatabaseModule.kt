package com.picpay.desafio.android.core.database.di


import com.picpay.desafio.android.core.database.DesafioDatabase
import com.picpay.desafio.android.core.database.fake.FakeDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val testingDatabaseModule = module {

    single { FakeDatabase.roomDatabaseBuilder(androidApplication()) }

    single { get<FakeDatabase>().usersDao() }

}