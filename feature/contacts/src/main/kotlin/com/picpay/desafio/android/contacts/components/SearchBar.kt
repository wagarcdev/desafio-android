package com.picpay.desafio.android.contacts.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.picpay.desafio.android.contacts.viewmodel.ContactUiEvent
import com.picpay.desafio.android.contacts.viewmodel.EventOrderChange
import com.picpay.desafio.android.contacts.viewmodel.EventSearchChange
import com.picpay.desafio.android.contacts.viewmodel.EventSortChange
import com.picpay.desafio.android.contacts.viewmodel.OrderDirection
import com.picpay.desafio.android.contacts.viewmodel.SearchUiState
import com.picpay.desafio.android.contacts.viewmodel.SortingParameter
import com.picpay.desafio.android.ui.theme.colorDetail
import com.picpay.desafio.android.ui.theme.picPayGreen

@Composable
fun SearchBar(
    searchUiState: SearchUiState,
    search: String,
    onEvent: (ContactUiEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var isSearchFieldFocused by remember { mutableStateOf(false) }

    Row(Modifier.fillMaxWidth()) {
        OutlinedTextField(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .onFocusChanged { focusState -> isSearchFieldFocused = focusState.isFocused }
                .height(70.dp)
                .weight(0.6f)
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
                if (search.isNotEmpty() && isSearchFieldFocused) {
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

        Spacer(modifier = Modifier.weight(0.025f))

        // Dropdown
        var expandedOrder by remember { mutableStateOf(false) }
        var expandedDirection by remember { mutableStateOf(false) }


        val boxWeight = 0.15f
        // Direction
        Box(
            modifier = Modifier
                .weight(boxWeight),
            contentAlignment = Alignment.Center,
        ) {

            OrderMenuButtonMask(
                isMenuExpanded = expandedDirection,
                label = "Ordem:",
                selectedItemText = searchUiState.orderingDirection.displayText,
                onClick = {
                    focusManager.clearFocus()
                    expandedDirection = true
                }
            )

            DropdownMenu(
                modifier = Modifier
                    .background(Color.DarkGray),
                expanded = expandedDirection,
                onDismissRequest = { expandedDirection = false }) {
                OrderDirection.entries.forEachIndexed { index, orderDirection ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .background(Color.DarkGray),
                        colors = MenuDefaults.itemColors(
                            textColor =
                            if (orderDirection == searchUiState.orderingDirection) picPayGreen
                            else Color.White
                        ),
                        onClick = {
                            onEvent(EventOrderChange(orderDirection))
                            expandedDirection = false
                        },
                        text = { Text(orderDirection.displayText) }
                    )
                    if (index != OrderDirection.entries.size - 1) {
                        HorizontalDivider(color = Color.Gray)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(0.025f))

        // Order
        Box(Modifier.weight(boxWeight), contentAlignment = Alignment.Center) {

            OrderMenuButtonMask(
                isMenuExpanded = expandedOrder,
                label = "Por:",
                selectedItemText = searchUiState.sortedBy.displayText,
                onClick = {
                    focusManager.clearFocus()
                    expandedOrder = true
                }
            )

            DropdownMenu(
                modifier = Modifier
                    .background(Color.DarkGray),
                expanded = expandedOrder,
                onDismissRequest = { expandedOrder = false }
            ) {
                SortingParameter.entries.forEachIndexed { index, sortingParameter ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .background(Color.DarkGray),
                        colors = MenuDefaults.itemColors(
                            textColor =
                            if (sortingParameter == searchUiState.sortedBy) picPayGreen
                            else Color.White
                        ),
                        onClick = {
                            onEvent(EventSortChange(sortingParameter))
                            expandedOrder = false
                        },
                        text = { Text(sortingParameter.displayText) }
                    )
                    if (index != SortingParameter.entries.size - 1) {
                        HorizontalDivider(color = Color.Gray)
                    }
                }
            }
        }
    }
}


@Composable
private fun OrderMenuButtonMask(
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

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(
        searchUiState = SearchUiState(),
        search = "",
        onEvent = {}
    )
}