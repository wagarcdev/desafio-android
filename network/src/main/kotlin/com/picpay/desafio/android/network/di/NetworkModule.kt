package com.picpay.desafio.android.network.di

import com.picpay.desafio.android.network.BuildConfig
import com.picpay.desafio.android.network.di.NetworkClient.retrofitBuilder
import org.koin.dsl.module

val networkModule = module {

    single { retrofitBuilder(BuildConfig.BASE_URL) }

}
