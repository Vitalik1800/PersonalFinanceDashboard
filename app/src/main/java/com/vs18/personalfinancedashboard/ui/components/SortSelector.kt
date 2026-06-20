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
import com.vs18.personalfinancedashboard.data.model.TransactionSort
import com.vs18.personalfinancedashboard.ui.theme.Dimens

@Composable
fun SortSelector(
    selected: TransactionSort,
    onSelected: (TransactionSort) -> Unit
) {

    Column(
        verticalArrangement =
            Arrangement.spacedBy(Dimens.SM)
    ) {

        Text(
            text = stringResource(R.string.sort_by),
            style =
                MaterialTheme
                    .typography
                    .titleMedium
        )

        Text(
            text =
                when (selected) {

                    TransactionSort.DATE_DESC ->
                        stringResource(R.string.newest_first)

                    TransactionSort.DATE_ASC ->
                        stringResource(R.string.oldest_first)

                    TransactionSort.AMOUNT_DESC ->
                        stringResource(R.string.highest_amount)

                    TransactionSort.AMOUNT_ASC ->
                        stringResource(R.string.lowest_amount)

                },
            style =
                MaterialTheme
                    .typography
                    .bodyMedium
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(Dimens.SM),
            verticalArrangement =
                Arrangement.spacedBy(Dimens.SM)
        ) {

            FilterChip(
                selected =
                    selected ==
                            TransactionSort.DATE_DESC,
                onClick = {
                    onSelected(
                        TransactionSort.DATE_DESC
                    )
                },
                label = {
                    Text(stringResource(R.string.newest))
                }
            )

            FilterChip(
                selected =
                    selected ==
                            TransactionSort.DATE_ASC,
                onClick = {
                    onSelected(
                        TransactionSort.DATE_ASC
                    )
                },
                label = {
                    Text(stringResource(R.string.oldest))
                }
            )

            FilterChip(
                selected =
                    selected ==
                            TransactionSort.AMOUNT_DESC,
                onClick = {
                    onSelected(
                        TransactionSort.AMOUNT_DESC
                    )
                },
                label = {
                    Text(stringResource(R.string.highest))
                }
            )

            FilterChip(
                selected =
                    selected ==
                            TransactionSort.AMOUNT_ASC,
                onClick = {
                    onSelected(
                        TransactionSort.AMOUNT_ASC
                    )
                },
                label = {
                    Text(stringResource(R.string.lowest))
                }
            )

        }

    }

}