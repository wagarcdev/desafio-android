package com.picpay.desafio.android.database.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.net.URL

suspend fun imageUrlToByteArray(imageUrl: String): ByteArray =
    withContext(Dispatchers.IO) {
        val outputStream = ByteArrayOutputStream()

        BitmapFactory
            .decodeStream(URL(imageUrl).openStream())
            .compress(Bitmap.CompressFormat.PNG, 100, outputStream)

        return@withContext outputStream.toByteArray()
    }