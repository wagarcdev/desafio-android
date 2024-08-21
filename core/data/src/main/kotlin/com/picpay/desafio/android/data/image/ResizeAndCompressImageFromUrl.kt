package com.picpay.desafio.android.data.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.net.URL

const val DEFAULT_IMAGE_TARGET_SIZE = 100 // Width || Height
const val DEFAULT_MAX_BYTES_SIZE = 50
const val DEFAULT_QUALITY = 75

suspend fun resizeAndCompressImageFromUrl(
    imageUrl: String,
    targetWidth: Int = DEFAULT_IMAGE_TARGET_SIZE,
    targetHeight: Int = DEFAULT_IMAGE_TARGET_SIZE,
    maxSizeInBytes: Int = DEFAULT_MAX_BYTES_SIZE
): ByteArray =
    withContext(Dispatchers.IO) {
        runCatching {

            val originalBitmap = BitmapFactory.decodeStream(URL(imageUrl).openStream())

            val resizeIsNeeded =
                originalBitmap.width > targetWidth || originalBitmap.height > targetHeight

            val outputBitmap = originalBitmap
                .takeUnless { resizeIsNeeded }
                ?: Bitmap.createScaledBitmap(
                    originalBitmap,
                    targetWidth,
                    targetHeight,
                    true
                )

            val outputStream = ByteArrayOutputStream()
            outputBitmap.compress(Bitmap.CompressFormat.JPEG, DEFAULT_QUALITY, outputStream)
            val compressedByteArray = outputStream.toByteArray()

            val furtherCompressionIsNeeded =
                compressedByteArray.size > maxSizeInBytes

            compressedByteArray
                .takeUnless { furtherCompressionIsNeeded }
                ?: compressImageFurther(compressedByteArray)

        }.getOrElse { exception ->
            exception.printStackTrace()
            byteArrayOf()
        }
    }

private suspend fun compressImageFurther(
    imageData: ByteArray,
    quality: Int = DEFAULT_QUALITY
): ByteArray =
    withContext(Dispatchers.IO) {
        val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        outputStream.toByteArray()
    }