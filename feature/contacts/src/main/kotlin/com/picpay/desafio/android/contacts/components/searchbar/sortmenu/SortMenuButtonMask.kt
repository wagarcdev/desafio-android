package com.picpay.desafio.android.contacts.components.searchbar.sortmenu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.ui.theme.picPayGreen

@Composable
fun SortMenuButtonMask(
    isMenuExpanded: Boolean,
    label: String,
    selectedItemText: String,
    onClick: () -> Unit,
) {
    val buttonTextColor = Color.White
    val buttonTextFontSize = 14.sp
    val buttonLabelColor = Color.Gray
    val buttonLabelFontSize = 12.sp

    Card(
        border = BorderStroke(
            width = 1.dp,
            color = if (isMenuExpanded) picPayGreen else Color.Gray
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable { onClick() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = label,
                fontSize = buttonLabelFontSize,
                color =
                if (isMenuExpanded) picPayGreen
                else buttonLabelColor
            )

            Text(
                text = selectedItemText,
                color = buttonTextColor,
                fontSize = buttonTextFontSize
            )
        }
    }
}