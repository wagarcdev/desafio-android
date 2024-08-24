package com.picpay.desafio.android.core.data.image

import com.picpay.desafio.android.core.data.image.fake.FakeImageCompressor
import com.picpay.desafio.android.core.data.image.fake.FakeImageDecoder
import com.picpay.desafio.android.core.data.image.fake.FakeImageProcessor
import com.picpay.desafio.android.core.data.image.model.ImageSize
import com.picpay.desafio.android.core.data.image.util.compressImageFromUrl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.InputStream

class ImageCompressorTest {

    private fun createFakeImageProcessor(
        decodeStreamResult: Pair<ByteArray, ImageSize>,
        compressImageResult: Pair<ByteArray, ImageSize>,
        scaleImageResult: Pair<ByteArray, ImageSize>,
        streamResult: InputStream
    ): FakeImageProcessor {
        val fakeImageDecoder = FakeImageDecoder(
            decodeStreamResult = decodeStreamResult,
            streamResult = streamResult
        )

        val fakeImageCompressor = FakeImageCompressor(
            compressImageResult = compressImageResult,
            scaleImageResult = scaleImageResult
        )

        return FakeImageProcessor(
            imageCompressor = fakeImageCompressor,
            imageDecoder = fakeImageDecoder
        )
    }

    @Test
    fun `resizeAndCompressImageFromUrl should compress image if it exceeds max size`() = runTest {
        // Given
        val imageUrl = "https://example.of/false_url_test_image.jpg"
        val maxSizeInBytes = 1000

        val fakeOriginalImageData = ByteArray(2000) { it.toByte() }
        val fakeDecodedImageSize = ImageSize(width = 200, height = 200)
        val fakeCompressedImageData = ByteArray(800) { it.toByte() }
        val fakeCompressedImageSize = ImageSize(width = 100, height = 100)
        val fakeInputStream = ByteArrayInputStream(fakeOriginalImageData)

        val fakeImageProcessor = createFakeImageProcessor(
            decodeStreamResult = Pair(fakeOriginalImageData, fakeDecodedImageSize),
            compressImageResult = Pair(fakeCompressedImageData, fakeCompressedImageSize),
            scaleImageResult = Pair(fakeCompressedImageData, fakeCompressedImageSize),
            streamResult = fakeInputStream
        )

        // When
        val result = compressImageFromUrl(
            imageUrl = imageUrl,
            imageProcessor = fakeImageProcessor,
            targetWidth = 100,
            targetHeight = 100,
            maxSizeInBytes = maxSizeInBytes
        )

        // Then
        assertTrue(result.size <= maxSizeInBytes)
    }

    @Test
    fun `resizeAndCompressImageFromUrl should not compress image if it is within max size`() = runTest {
        // Given
        val imageUrl = "https://example.of/false_url_test_image.jpg"
        val maxSizeInBytes = 1000
        val fakeImageDataWithinSize = ByteArray(800) { it.toByte() }
        val fakeImageSizeWithin = ImageSize(width = 200, height = 200)
        val fakeInputStream = ByteArrayInputStream(fakeImageDataWithinSize)

        val fakeImageProcessor = createFakeImageProcessor(
            decodeStreamResult = Pair(fakeImageDataWithinSize, fakeImageSizeWithin),
            compressImageResult = Pair(fakeImageDataWithinSize, fakeImageSizeWithin),
            scaleImageResult = Pair(fakeImageDataWithinSize, fakeImageSizeWithin),
            streamResult = fakeInputStream
        )

        // When
        val result = compressImageFromUrl(
            imageUrl = imageUrl,
            imageProcessor = fakeImageProcessor,
            targetWidth = 100,
            targetHeight = 100,
            maxSizeInBytes = maxSizeInBytes
        )

        // Then
        assertEquals(fakeImageDataWithinSize.size, result.size)
        assertTrue(result.size <= maxSizeInBytes)
        assertTrue(result.contentEquals(fakeImageDataWithinSize))
    }

}