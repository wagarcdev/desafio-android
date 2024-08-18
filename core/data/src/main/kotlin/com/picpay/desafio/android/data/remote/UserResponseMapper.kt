package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.domain.model.UserModel

fun UserResponse.toDomainModel() =
    UserModel(
        id = id,
        name = name,
        username = username,
        img = img
    )