package com.vs18.personalfinancedashboard.ui.transaction

import com.vs18.personalfinancedashboard.data.model.TransactionFilter
import com.vs18.personalfinancedashboard.data.model.TransactionSort
import com.vs18.personalfinancedashboard.data.model.TransactionType

sealed interface TransactionEvent {

    data class AmountChanged(
        val value: String
    ) : TransactionEvent

    data class CategoryChanged(
        val value: String
    ) : TransactionEvent

    data class NoteChanged(
        val value: String
    ) : TransactionEvent

    data class DateChanged(
        val value: Long
    ) : TransactionEvent

    data class TypeChanged(
        val value: TransactionType
    ) : TransactionEvent

    data class FilterChanged(
        val filter: TransactionFilter
    ) : TransactionEvent

    data class SortChanged(
        val sort: TransactionSort
    ) : TransactionEvent

    data object Save : TransactionEvent

    data object Update : TransactionEvent

    data object Delete : TransactionEvent

}