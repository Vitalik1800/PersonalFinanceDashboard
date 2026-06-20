package com.vs18.personalfinancedashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vs18.personalfinancedashboard.ui.theme.Dimens

@Composable
fun StatisticsCard(
    title: String,
    value: String
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = Dimens.XS
        )
    ) {

        Column(
            modifier = Modifier.padding(Dimens.MD),
            verticalArrangement = Arrangement.spacedBy(
                Dimens.SM
            )
        ) {

            Text(
                text = title,
                style =
                    MaterialTheme
                        .typography
                        .bodyMedium,
                color =
                    MaterialTheme
                        .colorScheme
                        .primary
            )

            HorizontalDivider()

            Text(
                text = value,
                style =
                    MaterialTheme
                        .typography
                        .headlineMedium
            )
        }

    }

}