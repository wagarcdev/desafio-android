package com.picpay.desafio.android.di

import android.util.Log
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.PicPayService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val okHttpModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor {
                    Log.d("OKHTTP", it)
                }.setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }
}

val retrofitModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }
}

val picPayServiceModule = module {
    single<PicPayService> {
        get<Retrofit>().create(PicPayService::class.java)
    }
}