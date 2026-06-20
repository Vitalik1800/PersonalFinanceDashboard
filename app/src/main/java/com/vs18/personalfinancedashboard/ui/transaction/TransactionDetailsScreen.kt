package com.vs18.personalfinancedashboard.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R
import com.vs18.personalfinancedashboard.ui.components.ConfirmationDialog
import com.vs18.personalfinancedashboard.ui.components.TransactionForm
import com.vs18.personalfinancedashboard.ui.theme.Dimens
import com.vs18.personalfinancedashboard.ui.theme.UpdateColor
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailsScreen(
    transactionId: Int,
    onBack: () -> Unit,
    viewModel: TransactionViewModel =
        koinViewModel()
) {

    val state =
        viewModel.uiState.collectAsState()

    var showDeleteDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(transactionId) {

        viewModel.loadTransaction(
            transactionId
        )

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.transaction_details))
                },
                navigationIcon = {

                    IconButton(
                        onClick = onBack
                    ) {

                        Icon(
                            imageVector =
                                Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )

                    }

                }
            )
        },

        bottomBar = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.MD),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        Dimens.SM
                    )
            ) {

                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UpdateColor
                    ),
                    onClick = {

                        viewModel.onEvent(
                            TransactionEvent.Update
                        )

                        onBack()

                    }
                ) {
                    Text(stringResource(R.string.update))
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        showDeleteDialog = true
                    },
                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                MaterialTheme.colorScheme.error,
                            contentColor =
                                MaterialTheme.colorScheme.onError
                        )
                ) {
                    Text(stringResource(R.string.delete))
                }

            }

        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(Dimens.MD),
            verticalArrangement =
                Arrangement.spacedBy(
                    Dimens.MD
                )
        ) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(Dimens.MD)
                ) {

                    Text(
                        text = stringResource(R.string.editing_transaction),
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Text(
                        text = stringResource(R.string.update_amount),
                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )

                }

            }

            TransactionForm(
                state = state.value,
                onEvent = viewModel::onEvent
            )

        }

        if (showDeleteDialog) {

            ConfirmationDialog(
                title = stringResource(R.string.delete_transaction),
                message = stringResource(R.string.are_you_sure),
                onConfirm = {

                    viewModel.onEvent(
                        TransactionEvent.Delete
                    )

                    onBack()

                    showDeleteDialog = false

                },
                onDismiss = {

                    showDeleteDialog = false

                }
            )

        }

    }
}