package com.picpay.desafio.android.contacts.viewmodel

data class SearchUiState(
    var searchQuery: String = "",
    var sortedBy: SortingParameter = SortingParameter.NAME,
    var orderingDirection: OrderDirection = OrderDirection.ASCENDING
)

enum class OrderDirection(val parameter: String, val displayText: String) {
    ASCENDING(parameter = "ASC", displayText = "A -> Z"),
    DESCENDING(parameter = "DESC", displayText = "Z -> A")
}

enum class SortingParameter(val parameter: String, val displayText: String) {
    NAME(parameter = "name", displayText = "Nome"),
    NICKNAME(parameter = "username", displayText = "@")
}

sealed interface ContactUiEvent
data class EventSearchChange(val search: String): ContactUiEvent
data class EventOrderChange(val orderDirection: OrderDirection): ContactUiEvent
data class EventSortChange(val sortingParameter: SortingParameter): ContactUiEvent
