package com.picpay.desafio.android.core.testing

import android.app.Application
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.core.testing.di.testingModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestDesafioApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(
                InstrumentationRegistry.getInstrumentation()
                    .targetContext.applicationContext
            )

            modules(testingModules)
        }
    }
}