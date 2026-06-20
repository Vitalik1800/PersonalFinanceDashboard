package com.vs18.personalfinancedashboard.ui.transaction

data class ValidationResult(
    val isValid: Boolean,
    val errorMessageRes: Int? = null
)