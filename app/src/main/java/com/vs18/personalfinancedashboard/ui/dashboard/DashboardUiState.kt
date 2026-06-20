package com.vs18.personalfinancedashboard.ui.dashboard

import com.vs18.personalfinancedashboard.data.db.entity.TransactionEntity

data class DashboardUiState(

    val isLoading: Boolean = false,

    val balance: Double = 0.0,

    val totalIncome: Double = 0.0,

    val totalExpenses: Double = 0.0,

    val latestTransactions:
        List<TransactionEntity> =
        emptyList()
)