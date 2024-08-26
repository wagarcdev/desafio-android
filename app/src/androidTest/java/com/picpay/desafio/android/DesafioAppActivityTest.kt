package com.picpay.desafio.android

import android.app.Activity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFalse

class DesafioAppActivityTest {

    private lateinit var activity: Activity

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

    @Test
    fun asserts_DecorFitsSystemWindows_isSet_to_False() {
        assertFalse(composeTestRule.activity.window.decorView.fitsSystemWindows)
    }
}