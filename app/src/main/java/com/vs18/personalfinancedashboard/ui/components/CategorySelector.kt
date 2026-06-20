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
import com.vs18.personalfinancedashboard.data.model.Category
import com.vs18.personalfinancedashboard.ui.theme.Dimens

@Composable
fun CategorySelector(
    selected: String,
    categories: List<Category>,
    onSelected: (String) -> Unit
) {

    Column(
        verticalArrangement =
            Arrangement.spacedBy(Dimens.SM)
    ) {

        Text(
            text = stringResource(R.string.category),
            style =
                MaterialTheme
                    .typography
                    .titleMedium
        )

        val selectedCategory = categories.find {
            it.key == selected
        }

        Text(
            text = "${stringResource(R.string.selected)} ${
                selectedCategory?.let {
                    stringResource(it.titleRes)
                } ?: stringResource(R.string.none)
            }"
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement =
                Arrangement.spacedBy(Dimens.SM),
            verticalArrangement =
                Arrangement.spacedBy(Dimens.SM)
        ) {

            categories.forEach { category ->

                FilterChip(
                    selected = selected == category.key,
                    onClick = {
                        onSelected(category.key)
                    },
                    label = {
                        Text(stringResource(category.titleRes))
                    }
                )

            }

        }

    }

}