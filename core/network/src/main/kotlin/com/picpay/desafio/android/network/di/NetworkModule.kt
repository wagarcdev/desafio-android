package com.picpay.desafio.android.network.di

import com.picpay.desafio.android.core.network.BuildConfig
import com.picpay.desafio.android.network.ConnectivityManagerNetworkMonitor
import com.picpay.desafio.android.network.NetworkMonitor
import com.picpay.desafio.android.network.di.NetworkClient.retrofitBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {

    single { retrofitBuilder(BuildConfig.BASE_URL) }

    single<NetworkMonitor> { ConnectivityManagerNetworkMonitor(androidContext()) }

}