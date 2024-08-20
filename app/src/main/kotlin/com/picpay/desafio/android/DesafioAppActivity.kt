package com.picpay.desafio.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.picpay.desafio.android.ui.theme.DesafioAppTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class DesafioAppActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesafioAppTheme {
                DesafioApp(calculateWindowSizeClass(this))
            }
        }
    }
}

