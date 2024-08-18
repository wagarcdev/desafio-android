package com.picpay.desafio.android.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.picpay.desafio.android.contacts.ContactsScreen
import com.picpay.desafio.android.contacts.ContactsScreenViewModel
import com.picpay.desafio.android.navigation.RootNavigationRoutes.CONTACTS_SCREEN
import org.koin.androidx.compose.koinViewModel

@Composable
fun RootNavigation(
    paddingValues: PaddingValues
) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier
            .padding(paddingValues),
        navController = navController,
        startDestination = CONTACTS_SCREEN
    ) {
        composable(CONTACTS_SCREEN) {

            val viewModel: ContactsScreenViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsState()

            ContactsScreen(
                uiState = uiState,
                onRetry = viewModel::fetchUsers
            )
        }
    }
}

object RootNavigationRoutes {
    const val CONTACTS_SCREEN = "CONTACTS_SCREEN"
}
