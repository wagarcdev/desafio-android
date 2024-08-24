package com.picpay.desafio.android.core.data.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.picpay.desafio.android.core.data.image.model.ImageSize
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.URL

class DefaultImageProcessor : ImageProcessor {

    override fun decodeStream(inputStream: InputStream): Pair<ByteArray, ImageSize> {
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val byteArray = bitmap.toByteArray()  // This method should be implemented as shown below.
        return Pair(byteArray, ImageSize(bitmap.width, bitmap.height))
    }

    override fun compressImage(imageData: ByteArray, quality: Int): Pair<ByteArray, ImageSize> {
        val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        val compressedByteArray = outputStream.toByteArray()
        return Pair(compressedByteArray, ImageSize(bitmap.width, bitmap.height))
    }

    override fun scaleImage(imageData: ByteArray, width: Int, height: Int, filter: Boolean): Pair<ByteArray, ImageSize> {
        val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, filter)
        return compressImage(scaledBitmap.toByteArray(), DEFAULT_QUALITY)
    }

    private fun Bitmap.toByteArray(quality: Int = 90): ByteArray =
        ByteArrayOutputStream().let {
            this.compress(Bitmap.CompressFormat.JPEG, quality, it)
            it.toByteArray()
        }

    override fun openStreamFromUrl(url: String): InputStream {
        return URL(url).openStream()
    }
}