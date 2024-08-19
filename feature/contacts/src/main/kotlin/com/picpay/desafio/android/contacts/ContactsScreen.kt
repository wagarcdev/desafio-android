package com.picpay.desafio.android.contacts

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.feature.contacts.R

@Composable
fun ContactsScreen(
    uiState: ContactsScreenUiState,
    onRetry: () -> Unit
) {
    val context = LocalContext.current

    LaunchedEffect(uiState.displayMessage) {
        if (uiState.displayMessage != null) {
            Toast.makeText(context, uiState.displayMessage, Toast.LENGTH_SHORT).show()
        }
    }

    if (uiState.showRetry) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { onRetry() },
                imageVector = Icons.Default.Refresh,
                contentDescription = stringResource(R.string.retry_icon),
                tint = Color.White
            )
            Text(text = stringResource(R.string.retry), color = Color.Gray)
        }
    } else {

        if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.Green)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    Text(
                        text = stringResource(R.string.contacts_screen_list_header),
                        fontSize = 28.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(
                    items = uiState.users,
                    key = { it.externalId }
                ) { user ->
                    ContactItem(user)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ContactsScreenPreview() {
    ContactsScreen(
        uiState = ContactsScreenUiState(),
        onRetry = { }
    )
}