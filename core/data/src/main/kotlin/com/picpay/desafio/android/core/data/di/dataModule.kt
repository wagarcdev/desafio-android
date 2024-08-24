package com.picpay.desafio.android.core.data.di

import com.picpay.desafio.android.core.data.sync.DataSyncManager
import com.picpay.desafio.android.core.data.util.ConnectivityManagerNetworkMonitor
import com.picpay.desafio.android.core.data.NetworkMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single { DataSyncManager() }

    single<NetworkMonitor> { ConnectivityManagerNetworkMonitor(androidContext()) }

}