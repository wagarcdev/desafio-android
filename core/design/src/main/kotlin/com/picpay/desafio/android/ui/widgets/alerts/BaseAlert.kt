package com.picpay.desafio.android.ui.widgets.alerts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.core.design.R

@Composable
fun BaseAlert(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    iconId: Int,
    iconDescription: String,
    iconSize: Dp = 250.dp,
    iconColor: Color = Color.Gray,
    optionalAlert: @Composable () -> Unit,
    alertText: String,
    alertTextStyle: TextStyle = TextStyle(
        color = iconColor,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
) {
    Box(
        modifier = modifier then Modifier
            .fillMaxSize(),
        contentAlignment = contentAlignment
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(iconSize),
                painter = painterResource(id = iconId),
                contentDescription = iconDescription,
                tint = Color.Gray
            )

            optionalAlert.invoke()

            Text(
                modifier = Modifier
                    .padding(
                        horizontal = 48.dp,
                        vertical = 60.dp
                        ),
                text = alertText,
                textAlign = TextAlign.Center,
                style = alertTextStyle
            )
        }
    }
}

@Preview
@Composable
private fun BaseAlertPreview() {
    BaseAlert(
        iconId = R.drawable.no_internet,
        iconDescription = "no internet image",
        optionalAlert = { /*TODO*/ },
        alertText = "Precisamos nos conectar pela primeira vez com a internet para buscar a lista de contatos"
    )
}
