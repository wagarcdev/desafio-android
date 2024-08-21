package com.picpay.desafio.android.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.picpay.desafio.android.DesafioAppState
import com.picpay.desafio.android.contacts.ContactScreen
import com.picpay.desafio.android.navigation.RootNavigationRoutes.CONTACTS_SCREEN

@Composable
fun RootNavigation(
    paddingValues: PaddingValues,
    appState: DesafioAppState
) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = appState.navController,
        startDestination = CONTACTS_SCREEN
    ) {

        composable(CONTACTS_SCREEN) { ContactScreen() }

    }
}

object RootNavigationRoutes {
    const val CONTACTS_SCREEN = "CONTACTS_SCREEN"
}
