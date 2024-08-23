package com.picpay.desafio.android.di

import com.picpay.desafio.android.contacts.di.featureContactsModule
import com.picpay.desafio.android.data.di.dataModule
import com.picpay.desafio.android.data.di.databaseModule
import com.picpay.desafio.android.datastore.di.dataStoreModule
import com.picpay.desafio.android.network.di.networkModule
import com.picpay.desafio.android.sync.work.di.syncModule
import org.koin.dsl.module

val desafioAppModule = module {
    includes(
        syncModule,
        networkModule,
        dataModule,
        dataStoreModule,
        databaseModule,
        featureContactsModule
    )
}