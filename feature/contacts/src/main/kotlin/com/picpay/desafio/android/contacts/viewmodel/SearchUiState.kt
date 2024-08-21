package com.picpay.desafio.android.contacts.viewmodel

data class SearchUiState(
    var searchQuery: String = "",
    var sortedBy: SortBy = SortBy.NAME,
    var orderDirection: OrderDirection = OrderDirection.ASCENDING
)

sealed interface SortingOption {
    val parameter: String
    val displayText: String
}
enum class OrderDirection(
    override val parameter: String,
    override val displayText: String
): SortingOption {
    ASCENDING(parameter = "ASC", displayText = "A -> Z"),
    DESCENDING(parameter = "DESC", displayText = "Z -> A")
}

enum class SortBy(
    override val parameter: String,
    override val displayText: String
): SortingOption {
    NAME(parameter = "name", displayText = "Nome"),
    NICKNAME(parameter = "username", displayText = "@")
}

sealed interface ContactUiEvent
data class EventSearchChange(val search: String): ContactUiEvent
data class EventOrderChange(val orderDirection: OrderDirection): ContactUiEvent
data class EventSortChange(val sortBy: SortBy): ContactUiEvent
