package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.contacts.di.contactsModule
import com.picpay.desafio.android.di.okHttpModule
import com.picpay.desafio.android.di.picPayServiceModule
import com.picpay.desafio.android.di.retrofitModule
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
                okHttpModule,
                retrofitModule,
                picPayServiceModule,
                contactsModule
            )
        }
    }
}