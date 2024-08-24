package com.picpay.desafio.android.core.data.image.fake

import com.picpay.desafio.android.core.data.image.ImageCompressor
import com.picpay.desafio.android.core.data.image.model.ImageSize

class FakeImageCompressor(
    private val compressImageResult: Pair<ByteArray, ImageSize> = Pair(ByteArray(0), ImageSize(0, 0)),
    private val scaleImageResult: Pair<ByteArray, ImageSize> = Pair(ByteArray(0), ImageSize(0, 0))
) : ImageCompressor {

    override fun compressImage(imageData: ByteArray, quality: Int): Pair<ByteArray, ImageSize> {
        return compressImageResult
    }

    override fun scaleImage(imageData: ByteArray, width: Int, height: Int, filter: Boolean): Pair<ByteArray, ImageSize> {
        return scaleImageResult
    }
}