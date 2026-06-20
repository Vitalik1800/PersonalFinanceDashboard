package com.vs18.personalfinancedashboard.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onDismiss,

        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },

        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium
            )
        },

        confirmButton = {

            TextButton(
                onClick = onConfirm
            ) {
                Text(
                    text = stringResource(R.string.delete),
                    color = MaterialTheme.colorScheme.error
                )
            }

        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {
                Text(stringResource(R.string.cancel))
            }

        }
    )

}