package com.picpay.desafio.android.core.data.model.mappers

import com.picpay.desafio.android.core.data.image.DefaultImageProcessor
import com.picpay.desafio.android.core.data.image.ImageProcessor
import com.picpay.desafio.android.core.data.image.compressImageFromUrl
import com.picpay.desafio.android.core.data.model.UserModel
import com.picpay.desafio.android.network.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun UserResponse.toDomainModel(processor: ImageProcessor = DefaultImageProcessor()) =
    withContext(Dispatchers.IO) {
        return@withContext UserModel(
            externalId = id,
            name = name,
            username = username,
            imgBytes = compressImageFromUrl(img, processor)
        )
    }
