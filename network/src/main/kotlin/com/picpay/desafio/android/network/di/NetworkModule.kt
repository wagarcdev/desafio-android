package com.picpay.desafio.android.network.di

import android.content.Context
import android.net.ConnectivityManager
import com.picpay.desafio.android.network.BuildConfig
import com.picpay.desafio.android.network.NetworkMonitor
import com.picpay.desafio.android.network.di.NetworkClient.retrofitBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {

    single { retrofitBuilder(BuildConfig.BASE_URL) }

    single { androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

    single { NetworkMonitor(get()) }

}