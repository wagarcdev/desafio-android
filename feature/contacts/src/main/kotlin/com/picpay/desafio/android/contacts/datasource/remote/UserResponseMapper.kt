package com.picpay.desafio.android.contacts.datasource.remote

import com.picpay.desafio.android.domain.model.UserModel

fun UserResponse.toDomainModel() =
    UserModel(
        externalId = id,
        name = name,
        username = username,
        img = img
    )