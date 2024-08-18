package com.picpay.desafio.android.contacts.datasource.remote

import retrofit2.Response
import retrofit2.http.GET


interface UserService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}