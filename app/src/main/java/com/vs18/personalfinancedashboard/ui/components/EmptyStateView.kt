package com.vs18.personalfinancedashboard.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R

@Composable
fun EmptyStateView() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment =
            Alignment.CenterHorizontally,
        verticalArrangement =
            Arrangement.Center
    ) {

        Icon(
            imageVector =
                Icons.AutoMirrored.Outlined.ReceiptLong,
            contentDescription = null
        )

        Text(
            text = stringResource(R.string.no_transactions),
            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )

        Text(
            text =
                stringResource(R.string.add_income_expense),
            style =
                MaterialTheme
                    .typography
                    .bodyMedium
        )

    }

}