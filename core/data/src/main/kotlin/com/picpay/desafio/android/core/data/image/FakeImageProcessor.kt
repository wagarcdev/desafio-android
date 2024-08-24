package com.picpay.desafio.android.core.data.image

import com.picpay.desafio.android.core.data.image.model.ImageSize
import java.io.InputStream

class FakeImageProcessor(
    val decodeStreamResult: Pair<ByteArray, ImageSize>,
    val compressImageResult: Pair<ByteArray, ImageSize>,
    val scaleImageResult: Pair<ByteArray, ImageSize>,
    val streamResult: InputStream

) : ImageProcessor {

    override fun decodeStream(inputStream: InputStream): Pair<ByteArray, ImageSize> =
        decodeStreamResult

    override fun compressImage(imageData: ByteArray, quality: Int): Pair<ByteArray, ImageSize> =
        compressImageResult

    override fun scaleImage(
        imageData: ByteArray,
        width: Int,
        height: Int,
        filter: Boolean
    ): Pair<ByteArray, ImageSize> =
        scaleImageResult

    override fun openStreamFromUrl(url: String): InputStream =
        streamResult
}