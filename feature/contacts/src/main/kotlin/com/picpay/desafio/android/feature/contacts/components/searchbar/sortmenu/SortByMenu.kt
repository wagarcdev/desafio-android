package com.picpay.desafio.android.feature.contacts.components.searchbar.sortmenu

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.picpay.desafio.android.contacts.viewmodel.ContactUiEvent
import com.picpay.desafio.android.contacts.viewmodel.EventOrderChange
import com.picpay.desafio.android.contacts.viewmodel.EventSortChange
import com.picpay.desafio.android.contacts.viewmodel.OrderDirection
import com.picpay.desafio.android.contacts.viewmodel.SearchUiState
import com.picpay.desafio.android.contacts.viewmodel.SortBy

@Composable
fun RowScope.SortByMenu(
    modifier: Modifier = Modifier,
    isExpanded: MutableState<Boolean>,
    searchUiState: SearchUiState,
    onEvent: (ContactUiEvent) -> Unit
) {
    BaseContactSortDropDownMenu(
        modifier = modifier,
        isExpanded = isExpanded,
        menuLabel = "Por:",
        selectedItemText = searchUiState.sortedBy.displayText,
        items = SortBy.entries.toTypedArray(),
        selectedItem = searchUiState.sortedBy,
        onSortingEvent = { sortingOption ->
            when(sortingOption) {
                is OrderDirection -> onEvent(EventOrderChange(sortingOption))
                is SortBy -> onEvent(EventSortChange(sortingOption))
            }
        }
    )
}