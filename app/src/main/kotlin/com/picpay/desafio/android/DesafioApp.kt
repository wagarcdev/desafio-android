package com.picpay.desafio.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.picpay.desafio.android.navigation.RootNavigation
import com.picpay.desafio.android.ui.theme.DesafioAppTheme
import com.picpay.desafio.android.ui.theme.colorPrimaryDark
import org.koin.compose.koinInject

@Composable
fun DesafioApp(
    windowSizeClass: WindowSizeClass,
    appState: DesafioAppState = rememberDesafioAppState(
        windowSizeClass = windowSizeClass,
        networkMonitor = koinInject()
    )
) {
    DesafioAppTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = colorPrimaryDark
        ) { paddingValues ->
            RootNavigation(
                paddingValues = paddingValues,
                appState = appState
            )
        }
    }
}