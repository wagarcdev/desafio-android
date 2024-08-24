package com.picpay.desafio.android.database.model.mappers

import com.picpay.desafio.android.database.image.resizeAndCompressImageFromUrl
import com.picpay.desafio.android.database.model.UserModel
import com.picpay.desafio.android.network.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun UserResponse.toDomainModel() =
    withContext(Dispatchers.IO) {
        return@withContext UserModel(
            externalId = id,
            name = name,
            username = username,
            imgBytes = resizeAndCompressImageFromUrl(img)
        )
    }
