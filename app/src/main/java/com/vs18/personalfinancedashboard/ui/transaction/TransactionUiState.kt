package com.vs18.personalfinancedashboard.ui.transaction

import com.vs18.personalfinancedashboard.data.db.entity.TransactionEntity
import com.vs18.personalfinancedashboard.data.model.TransactionFilter
import com.vs18.personalfinancedashboard.data.model.TransactionSort
import com.vs18.personalfinancedashboard.data.model.TransactionType

data class TransactionUiState(

    val transactions: List<TransactionEntity> =
        emptyList(),

    val selectedFilter:
        TransactionFilter =
        TransactionFilter.ALL,

    val selectedSort:
        TransactionSort =
        TransactionSort.DATE_DESC,

    val filteredTransactions:
        List<TransactionEntity> =
        emptyList(),

    val amount: String = "",

    val selectedType:
        TransactionType =
        TransactionType.EXPENSE,

    val selectedCategory: String = "",

    val note: String = "",

    val date: Long =
        System.currentTimeMillis(),

    val amountError: Int? = null,

    val categoryError: Int? = null,

    val noteError: Int? = null,

    val canSave: Boolean = false
)