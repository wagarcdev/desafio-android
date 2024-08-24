package com.picpay.desafio.android.core.data.image.fake

import com.picpay.desafio.android.core.data.image.ImageProcessor
import com.picpay.desafio.android.core.data.image.model.ImageSize
import java.io.InputStream

class FakeImageProcessor(
    private val imageDecoder: FakeImageDecoder,
    private val imageCompressor: FakeImageCompressor
) : ImageProcessor {

    override fun decodeStream(inputStream: InputStream): Pair<ByteArray, ImageSize> =
        imageDecoder.decodeStream(inputStream)

    override fun openStreamFromUrl(url: String): InputStream =
        imageDecoder.openStreamFromUrl(url)

    override fun compressImage(imageData: ByteArray, quality: Int): Pair<ByteArray, ImageSize> =
        imageCompressor.compressImage(imageData, quality)

    override fun scaleImage(
        imageData: ByteArray,
        width: Int,
        height: Int,
        filter: Boolean
    ): Pair<ByteArray, ImageSize> =
        imageCompressor.scaleImage(
            imageData,
            width,
            height,
            filter
        )
}