package com.picpay.desafio.android.network.services

import com.picpay.desafio.android.network.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET


interface UserService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>
}