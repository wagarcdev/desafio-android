package com.picpay.desafio.android.ui.widgets.alerts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.picpay.desafio.android.core.design.R

@Composable
fun NoInternetAlert(
    modifier: Modifier = Modifier
) =
    BaseAlert(
        modifier = modifier,
        iconId = R.drawable.no_internet,
        iconDescription = "no internet image",
        optionalAlert = { /*TODO*/ },
        alertText = "Precisamos nos conectar pela primeira vez com a internet para buscar a lista de contatos"
    )


@Preview
@Composable
private fun NoInternetAlertPreview() {
    NoInternetAlert()
}