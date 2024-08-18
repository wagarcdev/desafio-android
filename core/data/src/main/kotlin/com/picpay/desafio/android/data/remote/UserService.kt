package com.picpay.desafio.android.data.remote

import retrofit2.Response
import retrofit2.http.GET


interface UserService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}