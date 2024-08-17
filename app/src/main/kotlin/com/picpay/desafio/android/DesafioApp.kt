package com.picpay.desafio.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.picpay.desafio.android.navigation.RootNavigation
import com.picpay.desafio.android.ui.theme.colorPrimaryDark

@Composable
fun DesafioApp() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = colorPrimaryDark
    ) { paddingValues ->
        RootNavigation(paddingValues)
    }
}