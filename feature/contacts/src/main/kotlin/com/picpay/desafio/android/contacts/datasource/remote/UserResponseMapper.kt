package com.picpay.desafio.android.contacts.datasource.remote

import com.picpay.desafio.android.data.image.imageUrlToByteArray
import com.picpay.desafio.android.domain.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun UserResponse.toDomainModel() =
    withContext(Dispatchers.IO) {
        return@withContext UserModel(
            externalId = id,
            name = name,
            username = username,
            imgBytes = imageUrlToByteArray(img)
        )
    }
