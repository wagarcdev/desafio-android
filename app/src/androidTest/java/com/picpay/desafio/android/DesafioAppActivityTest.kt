package com.picpay.desafio.android

import android.app.Activity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.launchActivity
import androidx.test.platform.app.InstrumentationRegistry
import com.picpay.desafio.android.core.database.di.testingDatabaseModule
import com.picpay.desafio.android.core.testing.DesafioAppKoinTestRule
import com.picpay.desafio.android.di.desafioAppModule
import com.picpay.desafio.android.di.testingModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.KoinContext
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.assertFalse

class DesafioAppActivityTest : KoinTest {

    private lateinit var activity: Activity

    @get:Rule
    val testRule = DesafioAppKoinTestRule(
        modules = listOf(testingModules)
    )

    @Test
    fun asserts_DecorFitsSystemWindows_isSet_to_False() {
        launchActivity<DesafioAppActivity>().use { scenario ->
            scenario.onActivity { activity ->
                assertFalse(activity.window.decorView.fitsSystemWindows)
            }
        }
    }
}