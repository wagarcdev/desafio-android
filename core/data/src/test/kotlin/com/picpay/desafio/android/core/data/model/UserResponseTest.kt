package com.picpay.desafio.android.core.data.model

import com.picpay.desafio.android.core.data.image.FakeImageProcessor
import com.picpay.desafio.android.core.data.image.ImageProcessor
import com.picpay.desafio.android.core.data.image.model.ImageSize
import com.picpay.desafio.android.core.data.model.mappers.toDomainModel
import com.picpay.desafio.android.network.model.UserResponse
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import java.io.ByteArrayInputStream
import kotlin.test.assertContentEquals

class UserMapperTest {

    @Mock
    private lateinit var fakeImageProcessor: ImageProcessor

    private val imageUrl = "https://example.of/false_url_test_image.jpg"
    private val fakeImgBytes = ByteArray(100) { it.toByte() } // Example byte array for image data

    @Before
    fun setUp() {
        val fakeOriginalImageData = ByteArray(2000) { it.toByte() }
        val fakeCompressedImageData = fakeImgBytes // Ensure this matches what you expect
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
    fun `toDomainModel should convert UserResponse to UserModel correctly`() = runTest {
        // Given
        val userResponse = UserResponse(
            img = imageUrl,
            name = "John Doe",
            id = 123,
            username = "johndoe"
        )

        // When
        val result = userResponse.toDomainModel(fakeImageProcessor)

        // Then
        assertEquals(userResponse.id, result.externalId)
        assertEquals(userResponse.name, result.name)
        assertEquals(userResponse.username, result.username)
        assertContentEquals(fakeImgBytes, result.imgBytes)
    }
}