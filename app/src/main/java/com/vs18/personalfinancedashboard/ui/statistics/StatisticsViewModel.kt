package com.vs18.personalfinancedashboard.ui.statistics

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vs18.personalfinancedashboard.data.db.entity.TransactionEntity
import com.vs18.personalfinancedashboard.data.model.TransactionType
import com.vs18.personalfinancedashboard.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val repository:
        TransactionRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            StatisticsUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    init {

        viewModelScope.launch {

            repository
                .getAllTransactions()
                .collect { transactions ->

                    calculateStatistics(
                        transactions
                    )

                }

        }

    }

    @SuppressLint("SimpleDateFormat")
    private fun calculateStatistics(
        transactions:
            List<TransactionEntity>
    ) {

        val income =

            transactions
                .filter {

                    it.type ==
                            TransactionType.INCOME

                }
                .sumOf {

                    it.amount

                }

        val expenses =

            transactions
                .filter {

                    it.type ==
                            TransactionType.EXPENSE

                }
                .sumOf {

                    it.amount

                }

        val categoryStats =

            transactions
                .filter {

                    it.type ==
                            TransactionType.EXPENSE

                }
                .groupBy {

                    it.category

                }
                .mapValues {

                    entry ->

                    entry.value.sumOf {

                        it.amount

                    }

                }

        val monthlyStats =

            transactions
                .groupBy {

                    java.text.SimpleDateFormat(
                        "MM/yyyy"
                    ).format(
                        java.util.Date(
                            it.date
                        )
                    )

                }
                .mapValues {

                    entry ->

                    entry.value.sumOf {

                        if (
                            it.type ==
                            TransactionType.INCOME
                        ) {
                            it.amount
                        } else {
                            -it.amount
                        }

                    }

                }

        _uiState.update {

            it.copy(

                income =
                    income,

                expenses =
                    expenses,

                categoryStats =
                    categoryStats,

                monthlyStats =
                    monthlyStats

            )

        }

    }

}