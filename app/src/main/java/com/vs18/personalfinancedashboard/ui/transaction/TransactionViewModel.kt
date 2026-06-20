package com.vs18.personalfinancedashboard.ui.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vs18.personalfinancedashboard.data.db.entity.TransactionEntity
import com.vs18.personalfinancedashboard.data.model.TransactionFilter
import com.vs18.personalfinancedashboard.data.model.TransactionSort
import com.vs18.personalfinancedashboard.data.model.TransactionType
import com.vs18.personalfinancedashboard.data.repository.TransactionRepository
import com.vs18.personalfinancedashboard.utils.validateAmount
import com.vs18.personalfinancedashboard.utils.validateCategory
import com.vs18.personalfinancedashboard.utils.validateNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState

class TransactionViewModel(
    private val repository:
        TransactionRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            TransactionUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    private var currentTransaction:
            TransactionEntity? = null

    init {

        viewModelScope.launch {

            repository
                .getAllTransactions()
                .collect { transactions ->

                    _uiState.update {

                        it.copy(
                            transactions = transactions
                        )

                    }

                    applyFilters()

                }

        }

    }

    fun loadTransaction(
        id: Int
    ) {

        viewModelScope.launch {

            val transaction =
                repository.getTransactionById(id)

            currentTransaction =
                transaction

            _uiState.update {

                it.copy(
                    amount =
                        transaction.amount.toString(),

                    selectedType =
                        transaction.type,

                    selectedCategory =
                        transaction.category,

                    note =
                        transaction.note,

                    date =
                        transaction.date
                )

            }

        }

    }

    fun onEvent(
        event: TransactionEvent
    ) {

        when (event) {

            is TransactionEvent.AmountChanged -> {

                _uiState.update {

                    it.copy(
                        amount = event.value
                    )

                }

                validateForm()
            }

            is TransactionEvent.CategoryChanged -> {

                _uiState.update {

                    it.copy(
                        selectedCategory = event.value
                    )

                }

                validateForm()
            }

            is TransactionEvent.NoteChanged -> {

                _uiState.update {

                    it.copy(
                        note = event.value
                    )

                }

                validateForm()
            }

            is TransactionEvent.DateChanged -> {

                _uiState.update {

                    it.copy(
                        date = event.value
                    )

                }

                validateForm()
            }

            is TransactionEvent.TypeChanged -> {

                _uiState.update {

                    it.copy(
                        selectedType = event.value,
                        selectedCategory = ""
                    )

                }

                validateForm()
            }

            is TransactionEvent.FilterChanged -> {

                _uiState.update {

                    it.copy(
                        selectedFilter =
                            event.filter
                    )

                }

                applyFilters()
            }

            is TransactionEvent.SortChanged -> {

                _uiState.update {

                    it.copy(
                        selectedSort =
                            event.sort
                    )

                }

                applyFilters()
            }

            TransactionEvent.Save -> {
                saveTransaction()
            }

            TransactionEvent.Update -> {
                updateTransaction()
            }

            TransactionEvent.Delete -> {
                deleteTransaction()
            }

        }

    }

    private fun validateForm() {

        val state = uiState.value

        val amountResult =
            validateAmount(
                state.amount
            )

        val categoryResult =
            validateCategory(
                state.selectedCategory
            )

        val noteResult =
            validateNote(
                state.note
            )

        _uiState.update {

            it.copy(

                amountError =
                    amountResult.errorMessageRes,

                categoryError =
                    categoryResult.errorMessageRes,

                noteError =
                    noteResult.errorMessageRes,

                canSave =
                    amountResult.isValid &&
                    categoryResult.isValid &&
                    noteResult.isValid

            )

        }

    }

    private fun saveTransaction() {

        val state = uiState.value

        viewModelScope.launch {

            repository.addTransaction(
                amount = state.amount.toDouble(),
                type = state.selectedType,
                category = state.selectedCategory,
                note = state.note,
                date = state.date
            )

            _uiState.update {

                it.copy(
                    amount = "",
                    selectedCategory = "",
                    note = "",
                    canSave = false
                )

            }

        }

    }

    private fun updateTransaction() {

        val transaction =
            currentTransaction
                ?: return

        val state =
            uiState.value

        viewModelScope.launch {

            repository.updateTransaction(

                transaction.copy(

                    amount =
                        state.amount.toDouble(),

                    type =
                        state.selectedType,

                    category =
                        state.selectedCategory,

                    note =
                        state.note,

                    date =
                        state.date

                )

            )

        }

    }

    private fun deleteTransaction() {

        val transaction =
            currentTransaction
                ?: return

        viewModelScope.launch {

            repository.deleteTransaction(
                transaction
            )

        }

    }

    private fun applyFilters() {

        var result =
            uiState.value.transactions

        when (
            uiState.value.selectedFilter
        ) {

            TransactionFilter.ALL -> {}

            TransactionFilter.INCOME -> {

                result =
                    result.filter {

                        it.type == TransactionType.INCOME

                    }

            }

            TransactionFilter.EXPENSE -> {

                result =
                    result.filter {

                        it.type == TransactionType.EXPENSE

                    }

            }

        }

        result =
            when (
                uiState.value.selectedSort
            ) {

                TransactionSort.DATE_DESC ->
                    result.sortedByDescending {
                        it.date
                    }

                TransactionSort.DATE_ASC ->
                    result.sortedBy {
                        it.date
                    }

                TransactionSort.AMOUNT_DESC ->
                    result.sortedByDescending {
                        it.amount
                    }

                TransactionSort.AMOUNT_ASC ->
                    result.sortedBy {
                        it.amount
                    }

            }

        _uiState.update {

            it.copy(
                filteredTransactions =
                    result
            )

        }
    }

}