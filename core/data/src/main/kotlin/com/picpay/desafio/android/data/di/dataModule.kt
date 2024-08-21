package com.picpay.desafio.android.data.di

import com.picpay.desafio.android.data.sync.DataSyncManager
import com.picpay.desafio.android.data.util.ConnectivityManagerNetworkMonitor
import com.picpay.desafio.android.network.NetworkMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single { DataSyncManager() }

    single<NetworkMonitor> { ConnectivityManagerNetworkMonitor(androidContext()) }

}