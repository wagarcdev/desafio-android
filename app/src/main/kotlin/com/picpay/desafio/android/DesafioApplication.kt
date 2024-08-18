package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.contacts.di.featureContactsModule
import com.picpay.desafio.android.network.di.networkModule
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
                featureContactsModule
            )
        }
    }
}