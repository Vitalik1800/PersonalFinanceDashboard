package com.vs18.personalfinancedashboard.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vs18.personalfinancedashboard.data.preferences.ThemePreferences
import com.vs18.personalfinancedashboard.data.repository.TransactionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository:
        TransactionRepository,
    private val themePreferences:
        ThemePreferences
) : ViewModel() {

    private val _uiState =
        MutableStateFlow(
            SettingsUiState()
        )

    val uiState =
        _uiState.asStateFlow()

    init {

        viewModelScope.launch {

            themePreferences
                .isDarkTheme
                .collect { isDarkTheme ->

                    _uiState.update {

                        it.copy(
                            isDarkTheme =
                                isDarkTheme
                        )

                    }

                }

        }

    }

    fun onEvent(
        event: SettingsEvent
    ) {

        when (event) {

            SettingsEvent.ThemeToggled -> {

                toggleTheme()

            }

            SettingsEvent.ClearData -> {

                clearData()

            }

        }

    }

    private fun toggleTheme() {

        viewModelScope.launch {

            val newValue =
                !_uiState.value.isDarkTheme

            themePreferences.saveTheme(
                newValue
            )

        }

    }

    private fun clearData() {

        viewModelScope.launch {

            repository
                .clearAllTransactions()

        }

    }

}