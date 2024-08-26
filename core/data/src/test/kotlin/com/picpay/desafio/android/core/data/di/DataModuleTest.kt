package com.picpay.desafio.android.core.data.di

import android.content.Context
import com.picpay.desafio.android.core.data.image.AppImageCompressor
import com.picpay.desafio.android.core.data.image.AppImageDecoder
import com.picpay.desafio.android.core.data.image.AppImageProcessor
import com.picpay.desafio.android.core.data.network.NetworkMonitor
import com.picpay.desafio.android.core.data.sync.SyncManager
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify


@OptIn(KoinExperimentalAPI::class)
class DataModuleTest {

    @Test
    fun checkDataModule() {
        dataModule.verify(
            extraTypes = listOf(
                Context::class,
                SyncManager::class,
                NetworkMonitor::class,
                AppImageProcessor::class,
                AppImageDecoder::class,
                AppImageCompressor::class
            )
        )
    }
}