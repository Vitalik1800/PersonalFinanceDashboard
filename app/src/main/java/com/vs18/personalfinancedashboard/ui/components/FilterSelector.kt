package com.vs18.personalfinancedashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R
import com.vs18.personalfinancedashboard.data.model.TransactionFilter
import com.vs18.personalfinancedashboard.ui.theme.Dimens

@Composable
fun FilterSelector(
    selected: TransactionFilter,
    onSelected: (TransactionFilter) -> Unit
) {

    Column(
        verticalArrangement =
            Arrangement.spacedBy(Dimens.SM)
    ) {

        Text(
            text = stringResource(R.string.filter),
            style =
                MaterialTheme
                    .typography
                    .titleMedium
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(Dimens.SM),
            verticalArrangement =
                Arrangement.spacedBy(Dimens.SM)
        ) {

            TransactionFilter.entries.forEach { filter ->

                FilterChip(
                    selected = selected == filter,
                    onClick = {
                        onSelected(filter)
                    },
                    label = {
                        Text(
                            when (filter) {
                                TransactionFilter.ALL ->
                                    stringResource(R.string.all)

                                TransactionFilter.INCOME ->
                                    stringResource(R.string.income)

                                TransactionFilter.EXPENSE ->
                                    stringResource(R.string.expense)
                            }
                        )
                    }
                )

            }

        }

    }

}