package com.vs18.personalfinancedashboard.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R
import com.vs18.personalfinancedashboard.data.model.ExpenseCategories
import com.vs18.personalfinancedashboard.data.model.IncomeCategories
import com.vs18.personalfinancedashboard.data.model.TransactionType
import com.vs18.personalfinancedashboard.ui.theme.Dimens
import com.vs18.personalfinancedashboard.ui.theme.Error
import com.vs18.personalfinancedashboard.ui.theme.Success
import com.vs18.personalfinancedashboard.ui.theme.UpdateColor
import com.vs18.personalfinancedashboard.ui.transaction.TransactionEvent
import com.vs18.personalfinancedashboard.ui.transaction.TransactionUiState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("NonObservableLocale")
@Composable
fun TransactionForm(
    state: TransactionUiState,
    onEvent: (TransactionEvent) -> Unit
) {

    val categories =
        if (state.selectedType == TransactionType.INCOME)
            IncomeCategories.values
        else
            ExpenseCategories.values

    val formattedDate = SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()
    ).format(Date(state.date))

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {


        Column(
            modifier = Modifier
                .padding(Dimens.MD),
            verticalArrangement = Arrangement.spacedBy(Dimens.MD)
        ) {

            OutlinedTextField(
                value = state.amount,
                onValueChange = {
                    onEvent(
                        TransactionEvent.AmountChanged(it)
                    )
                },
                label = {
                    Text(stringResource(R.string.amount))
                },
                isError = state.amountError != null,
                modifier = Modifier.fillMaxWidth()
            )

            state.amountError?.let { errorRes ->
                Text(
                    text = stringResource(errorRes),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = stringResource(R.string.transaction_type),
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SM)
            ) {

                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Success
                    ),
                    onClick = {
                        onEvent(
                            TransactionEvent.TypeChanged(
                                TransactionType.INCOME
                            )
                        )
                    }
                ) {
                    Text(stringResource(R.string.income))
                }

                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Error
                    ),
                    onClick = {
                        onEvent(
                            TransactionEvent.TypeChanged(
                                TransactionType.EXPENSE
                            )
                        )
                    }
                ) {
                    Text(stringResource(R.string.expense))
                }

            }

            CategorySelector(
                selected = state.selectedCategory,
                categories = categories,
                onSelected = {
                    onEvent(
                        TransactionEvent.CategoryChanged(it)
                    )
                }
            )

            state.categoryError?.let { errorRes ->
                Text(
                    text = stringResource(errorRes),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            OutlinedTextField(
                value = state.note,
                onValueChange = {
                    onEvent(
                        TransactionEvent.NoteChanged(it)
                    )
                },
                label = {
                    Text(stringResource(R.string.note))
                },
                isError = state.noteError != null,
                modifier = Modifier.fillMaxWidth()
            )

            state.noteError?.let { errorRes ->
                Text(
                    text = stringResource(errorRes),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(Dimens.MD),
                    verticalArrangement =
                        Arrangement.spacedBy(Dimens.SM)
                ) {

                    Text(
                        text = stringResource(R.string.transaction_date),
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Text(
                        text = formattedDate,
                        style =
                            MaterialTheme
                                .typography
                                .bodyLarge
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = UpdateColor
                        ),
                        onClick = {
                            showDatePicker = true
                        }
                    ) {
                        Text(stringResource(R.string.change_date))
                    }

                }

                if (showDatePicker) {

                    val datePickerState =
                        rememberDatePickerState(
                            initialSelectedDateMillis =
                                state.date
                        )

                    DatePickerDialog(

                        onDismissRequest = {
                            showDatePicker = false
                        },

                        confirmButton = {

                            TextButton(

                                onClick = {

                                    datePickerState
                                        .selectedDateMillis
                                        ?.let {

                                            onEvent(
                                                TransactionEvent.DateChanged(
                                                    it
                                                )
                                            )

                                        }

                                    showDatePicker = false

                                }

                            ) {

                                Text(stringResource(R.string.ok))

                            }

                        },

                        dismissButton = {

                            TextButton(
                                onClick = {
                                    showDatePicker = false
                                }
                            ) {
                                Text(stringResource(R.string.cancel))
                            }

                        }

                    ) {

                        DatePicker(
                            state = datePickerState
                        )

                    }

                }

            }

        }

    }
}