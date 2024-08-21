package com.picpay.desafio.android.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.core.design.R

@Composable
fun NoInternetAlert() {
    Box(
        Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(250.dp),
            painter = painterResource(id = R.drawable.no_internet),
            contentDescription = "no internet image",
            tint = Color.Gray
        )
    }
}

@Preview
@Composable
private fun NoInternetAlertPreview() {
    NoInternetAlert()
}