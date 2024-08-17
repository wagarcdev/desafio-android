package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.di.networkModule
import com.picpay.desafio.android.contacts.di.featureContactsModule
import com.picpay.desafio.android.data.di.repositoryModule
import com.picpay.desafio.android.domain.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DesafioApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DesafioApplication)
            modules(
                networkModule,
                repositoryModule,
                useCasesModule,
                featureContactsModule
            )
        }
    }
}