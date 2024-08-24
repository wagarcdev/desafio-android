package com.picpay.desafio.android.core.data.image.fake

import com.picpay.desafio.android.core.data.image.ImageDecoder
import com.picpay.desafio.android.core.data.image.model.ImageSize
import java.io.InputStream

class FakeImageDecoder(
    private val decodeStreamResult: Pair<ByteArray, ImageSize>,
    private val streamResult: InputStream
): ImageDecoder {
    override fun decodeStream(inputStream: InputStream): Pair<ByteArray, ImageSize> =
        decodeStreamResult

    override fun openStreamFromUrl(url: String): InputStream =
        streamResult
}