package com.picpay.desafio.android.data.di

import android.util.Log
import com.picpay.desafio.android.core.data.BuildConfig
import com.picpay.desafio.android.data.remote.PicPayService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor {
                    Log.d("OKHTTP", it)
                }.setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single<PicPayService> {
        get<Retrofit>().create(PicPayService::class.java)
    }
}