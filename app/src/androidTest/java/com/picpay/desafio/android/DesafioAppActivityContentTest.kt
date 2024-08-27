package com.picpay.desafio.android

import android.app.Activity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.core.database.di.testingDatabaseModule
import com.picpay.desafio.android.core.testing.DesafioAppKoinTestRule
import com.picpay.desafio.android.di.desafioAppModule
import com.picpay.desafio.android.di.testingModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.KoinContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.assertFalse

class DesafioAppActivityContentTest: KoinTest {

    private lateinit var activity: Activity

    @get:Rule
    val testRule = DesafioAppKoinTestRule(
        modules = listOf(testingModules)
    )

    @get:Rule
    val composeTestRule = createAndroidComposeRule<DesafioAppActivity>()

    @Before
    fun setUp() {
        activity = composeTestRule.activity
    }

    @Test
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    fun testActivityContent() {
        composeTestRule.activity.setContent {
                DesafioApp(calculateWindowSizeClass(activity))
        }

        composeTestRule.onNodeWithTag(APP_TEST_TAG)
            .assertIsDisplayed()
    }
}