package com.picpay.desafio.android.di

import com.picpay.desafio.android.core.data.di.test.testingDataModule
import com.picpay.desafio.android.core.database.di.testingDatabaseModule
import com.picpay.desafio.android.core.datastore.di.test.testingDatastoreModule
import com.picpay.desafio.android.core.domain.di.testingDomainModule
import com.picpay.desafio.android.core.network.di.test.testingNetworkModule
import com.picpay.desafio.android.feature.contacts.di.test.testingFeatureContactsModule
import com.picpay.desafio.android.sync.work.di.test.testingSyncModule
import org.koin.dsl.module

val testingModules = module {
    includes(
        testingDataModule,
        testingSyncModule,
        testingNetworkModule,
        testingDomainModule,
        testingDatastoreModule,
        testingDatabaseModule,
        testingFeatureContactsModule

    )
}