package com.picpay.desafio.android.core.datastore.di

import com.picpay.desafio.android.core.datastore.DesafioAppDataStoreRepository
import com.picpay.desafio.android.core.datastore.DesafioAppPreferencesDataSource
import com.picpay.desafio.android.core.datastore.PreferencesDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {

    single { DesafioAppDataStoreRepository(androidContext()) }

    single<PreferencesDataSource> { DesafioAppPreferencesDataSource(get()) }

}
