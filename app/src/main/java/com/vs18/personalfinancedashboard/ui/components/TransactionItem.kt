package com.vs18.personalfinancedashboard.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R
import com.vs18.personalfinancedashboard.data.db.entity.TransactionEntity
import com.vs18.personalfinancedashboard.data.model.TransactionType
import com.vs18.personalfinancedashboard.ui.theme.Dimens
import com.vs18.personalfinancedashboard.ui.theme.Error
import com.vs18.personalfinancedashboard.ui.theme.Success
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("NonObservableLocale")
@Composable
fun TransactionItem(
    transaction: TransactionEntity,
    onClick: (Int) -> Unit
) {

    val formattedDate =
        SimpleDateFormat(
            "dd.MM.yyyy",
            Locale.getDefault()
        ).format(
            Date(transaction.date)
        )

    val amountColor =
        if (
            transaction.type ==
            TransactionType.INCOME
        ) {
            Success
        } else {
            Error
        }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.XS)
            .clickable {
                onClick(transaction.id)
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.MD),
            horizontalArrangement =
                Arrangement.SpaceBetween,
            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = transaction.category,
                    style =
                        MaterialTheme
                            .typography
                            .titleMedium
                )

                Text(
                    text =
                        if (
                            transaction.type ==
                            TransactionType.INCOME
                        ) {
                            stringResource(R.string.income)
                        } else {
                            stringResource(R.string.expense)
                        },
                    style =
                        MaterialTheme
                            .typography
                            .labelMedium
                )

                if (
                    transaction.note.isNotBlank()
                ) {

                    Text(
                        text = transaction.note,
                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )

                }

                Text(
                    text = formattedDate,
                    style =
                        MaterialTheme
                            .typography
                            .bodySmall
                )

            }

            Text(
                text =
                    if (
                        transaction.type ==
                        TransactionType.INCOME
                    ) {
                        "+${transaction.amount}"
                    } else {
                        "-${transaction.amount}"
                    },
                color = amountColor,
                style =
                    MaterialTheme
                        .typography
                        .titleMedium
            )

        }

    }

}