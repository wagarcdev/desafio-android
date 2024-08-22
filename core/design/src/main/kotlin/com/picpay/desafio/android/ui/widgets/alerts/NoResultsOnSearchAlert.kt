package com.picpay.desafio.android.ui.widgets.alerts

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.core.design.R

@Composable
fun NoResultsOnSearchAlert(
    modifier: Modifier = Modifier
) =
    BaseAlert(
        modifier = modifier
            .padding(top = 32.dp),
        contentAlignment = Alignment.TopCenter,
        iconId = R.drawable.magnifying_glass,
        iconSize = 120.dp,
        iconDescription = "no internet image",
        optionalAlert = {
            Text(
                modifier = modifier
                    .padding(top = 36.dp),
                text = "Desculpe...",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        alertText = "Sua busca não retornou resultados...",
        alertTextStyle = TextStyle(
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    )


@Preview
@Composable
private fun NoResultsOnSearchPreview() {
    NoResultsOnSearchAlert()
}