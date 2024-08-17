package com.picpay.desafio.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.picpay.desafio.android.ui.theme.DesafioAppTheme

class DesafioAppActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesafioAppTheme {
                DesafioApp()
            }
        }
    }
}

