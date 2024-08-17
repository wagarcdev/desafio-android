package com.picpay.desafio.android.contacts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.feature.contacts.R

@Composable
fun ContactsScreen(
    uiState: ContactsScreenUiState
) {
    LazyColumn(
        modifier = Modifier
            .padding(24.dp            ),
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
            key = { it.id }
        ) { user ->
            ContactItem(user)
        }
    }
}

@Preview
@Composable
private fun ContactsScreenPreview() {
    ContactsScreen(ContactsScreenUiState())
}