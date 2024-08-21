package com.picpay.desafio.android.contacts.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.feature.contacts.R

@Composable
fun ContactsScreenTitle() {
    Text(
        modifier = Modifier
            .padding(vertical = 21.dp),
        text = stringResource(R.string.contacts_screen_list_header),
        fontSize = 28.sp,
        color = Color.White,
        fontWeight = FontWeight.Bold
    )
}