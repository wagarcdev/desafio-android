package com.picpay.desafio.android.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier, contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.Green)
    }
}