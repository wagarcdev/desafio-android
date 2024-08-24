package com.picpay.desafio.android.core.data.image

import com.picpay.desafio.android.core.data.image.model.ImageSize
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.io.ByteArrayInputStream
import kotlin.test.assertTrue

class ImageCompressorTest {

    private lateinit var fakeImageProcessor: FakeImageProcessor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        // Setup fake data for testing
        val fakeOriginalImageData = ByteArray(2000) { it.toByte() }
        val fakeCompressedImageData = ByteArray(800) { it.toByte() }
        val fakeImageSize = ImageSize(width = 100, height = 100)
        val fakeInputStream = ByteArrayInputStream(fakeOriginalImageData)

        fakeImageProcessor = FakeImageProcessor(
            decodeStreamResult = Pair(fakeOriginalImageData, fakeImageSize),
            compressImageResult = Pair(fakeCompressedImageData, fakeImageSize),
            scaleImageResult = Pair(fakeOriginalImageData, fakeImageSize),
            streamResult = fakeInputStream
        )
    }


    @Test
    fun `resizeAndCompressImageFromUrl should compress image if it exceeds max size`() = runTest {
        // Given
        val imageUrl = "https://example.of/false_url_test_image.jpg"
        val maxSizeInBytes = 1000
        val expectedCompressedSize = fakeImageProcessor.compressImageResult.first.size

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
        assertEquals(expectedCompressedSize, result.size)
    }

    @Test
    fun `resizeAndCompressImageFromUrl should not compress image if it is within max size`() = runBlocking {
        // Given
        val imageUrl = "https://example.of/false_url_test_image.jpg"
        val maxSizeInBytes = 2000

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
        assertEquals(fakeImageProcessor.compressImageResult.first.size, result.size)
    }

}