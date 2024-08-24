package com.picpay.desafio.android.core.data.di

import com.picpay.desafio.android.core.data.NetworkMonitor
import com.picpay.desafio.android.core.data.sync.SyncManager
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify


@OptIn(KoinExperimentalAPI::class)
class DesafioAppModuleTest {

    @Test
    fun checkDataModule() {
        dataModule.verify(
            extraTypes = listOf(
                SyncManager::class,
                NetworkMonitor::class,
            )
        )
    }
}