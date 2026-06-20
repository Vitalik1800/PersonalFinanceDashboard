package com.vs18.personalfinancedashboard.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R
import com.vs18.personalfinancedashboard.ui.components.TransactionForm
import com.vs18.personalfinancedashboard.ui.theme.Dimens
import com.vs18.personalfinancedashboard.ui.theme.Success
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    onBack: () -> Unit,
    viewModel: TransactionViewModel = koinViewModel()
) {

    val state = viewModel.uiState.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(stringResource(R.string.add_transaction))
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

            Button(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(Dimens.MD),

                enabled = state.value.canSave,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Success
                ),

                onClick = {
                    viewModel.onEvent(
                        TransactionEvent.Save
                    )

                    onBack()
                }
            ) {
                Text(stringResource(R.string.save))
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

            TransactionForm(
                state = state.value,
                onEvent = viewModel::onEvent
            )

        }
    }
}