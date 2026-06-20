package com.vs18.personalfinancedashboard.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vs18.personalfinancedashboard.data.db.entity.TransactionEntity
import com.vs18.personalfinancedashboard.data.model.TransactionType
import com.vs18.personalfinancedashboard.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repository:
        TransactionRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            DashboardUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    init {

        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {

            repository
                .getAllTransactions()
                .collect { transactions ->

                    calculateSummary(
                        transactions
                    )

                }

        }

    }

    private fun calculateSummary(
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

        val balance =
            income - expenses

        val latestTransactions =

            transactions
                .sortedByDescending {

                    it.date

                }
                .take(5)

        _uiState.update {

            it.copy(

                balance =
                    balance,

                totalIncome =
                    income,

                totalExpenses =
                    expenses,

                latestTransactions =
                    latestTransactions,

                isLoading = false

            )

        }
    }

}