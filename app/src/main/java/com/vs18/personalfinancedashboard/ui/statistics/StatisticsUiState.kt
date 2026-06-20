package com.vs18.personalfinancedashboard.ui.statistics

data class StatisticsUiState(

    val isLoading: Boolean = false,

    val income: Double = 0.0,

    val expenses: Double = 0.0,

    val categoryStats:
        Map<String, Double> =
        emptyMap(),

    val monthlyStats:
        Map<String, Double> =
        emptyMap()
)