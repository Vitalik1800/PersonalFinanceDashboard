package com.vs18.personalfinancedashboard.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R
import com.vs18.personalfinancedashboard.ui.transaction.ValidationResult

fun validateAmount(
    amount: String
) : ValidationResult {

    if (amount.isBlank()) {
        return ValidationResult(
            false,
            R.string.amount_is_required
        )
    }

    val value =
        amount.toDoubleOrNull()

    if (value == null) {
        return ValidationResult(
            false,
            R.string.invalid_amount
        )
    }

    if (value <= 0) {
        return ValidationResult(
            false,
            R.string.amount_must_be_greater
        )
    }

    if (value > 999999999) {
        return ValidationResult(
            false,
            R.string.amount_to_large
        )
    }

    return ValidationResult(true)
}

fun validateCategory(
    category: String
): ValidationResult {

    return if (
        category.isBlank()
    ) {

        ValidationResult(
            false,
            R.string.category_is_required
        )

    } else {

        ValidationResult(true)

    }
}

fun validateNote(
    note: String
): ValidationResult {

    return if (
        note.length > 200
    ) {

        ValidationResult(
            false,
            R.string.maximum_200
        )

    } else {

        ValidationResult(true)

    }

}