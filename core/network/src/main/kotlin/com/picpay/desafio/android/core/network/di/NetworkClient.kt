package com.picpay.desafio.android.core.network.di

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClient {

    private fun okHttpClientBuilder() = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor {
                Log.d("OKHTTP", it)
            }.setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    fun retrofitBuilder(
        baseUrl: String,
        okHttpClient: OkHttpClient = okHttpClientBuilder()
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}