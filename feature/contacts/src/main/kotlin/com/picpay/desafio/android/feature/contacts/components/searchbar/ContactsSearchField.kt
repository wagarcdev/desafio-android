package com.picpay.desafio.android.feature.contacts.components.searchbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactUiEvent
import com.picpay.desafio.android.feature.contacts.viewmodel.EventSearchChange
import com.picpay.desafio.android.ui.theme.colorDetail
import com.picpay.desafio.android.ui.theme.picPayGreen

@Composable
fun RowScope.ContactsSearchField(
    modifier: Modifier = Modifier,
    isSearchFieldFocused: MutableState<Boolean>,
    search: String,
    onEvent: (ContactUiEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier then Modifier
            .onFocusChanged { focusState -> isSearchFieldFocused.value = focusState.isFocused }
            .height(70.dp)
            .padding(bottom = 12.dp),
        value = search,
        onValueChange = { onEvent(EventSearchChange(it)) },
        textStyle = TextStyle(color = Color.White),
        placeholder = { Text(text = "Pesquisar", color = colorDetail) },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = { focusManager.clearFocus() }
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedIndicatorColor = picPayGreen,
            unfocusedIndicatorColor = colorDetail,
            cursorColor = picPayGreen,
        ),
        trailingIcon = {
            if (search.isNotEmpty() && isSearchFieldFocused.value) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.DarkGray),
                    modifier = Modifier
                        .clickable { onEvent(EventSearchChange("")) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear search",
                        tint = Color.Gray
                    )
                }
            }
        }
    )
}