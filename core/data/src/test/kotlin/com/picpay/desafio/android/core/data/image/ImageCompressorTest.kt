package com.picpay.desafio.android.core.data.image

import com.picpay.desafio.android.core.data.image.model.ImageSize
import com.picpay.desafio.android.core.data.image.util.compressImageFromUrl
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.ByteArrayInputStream

class ImageCompressorTest {

    @Test
    fun `resizeAndCompressImageFromUrl should compress image if it exceeds max size`() = runTest {
        // Given
        val imageUrl = "https://example.of/false_url_test_image.jpg"
        val maxSizeInBytes = 1000

        val fakeImageProcessor = createFakeImageProcessor()

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