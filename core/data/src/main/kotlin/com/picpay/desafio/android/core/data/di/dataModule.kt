package com.picpay.desafio.android.core.data.di

import com.picpay.desafio.android.core.data.image.DesafioAppImageProcessor
import com.picpay.desafio.android.core.data.image.ImageProcessor
import com.picpay.desafio.android.core.data.image.compressor.DesafioAppImageCompressor
import com.picpay.desafio.android.core.data.image.decoder.DesafioAppImageDecoder
import com.picpay.desafio.android.core.data.network.ConnectivityManagerNetworkMonitor
import com.picpay.desafio.android.core.data.network.NetworkMonitor
import com.picpay.desafio.android.core.data.sync.DataSyncManager
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {

    single { DataSyncManager() }

    singleOf(::ConnectivityManagerNetworkMonitor) { bind<NetworkMonitor>() }

    single { DesafioAppImageDecoder() }

    single { DesafioAppImageCompressor() }

    singleOf(::DesafioAppImageProcessor) { bind<ImageProcessor>() }

}