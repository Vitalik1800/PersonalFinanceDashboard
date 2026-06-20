package com.vs18.personalfinancedashboard.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R
import com.vs18.personalfinancedashboard.ui.components.ConfirmationDialog
import com.vs18.personalfinancedashboard.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel =
        koinViewModel()
) {

    val state =
        viewModel.uiState.collectAsState()

    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(stringResource(R.string.settings))
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

        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimens.MD),
            verticalArrangement =
                Arrangement.spacedBy(
                    Dimens.MD
                )
        ) {

            Card(
                modifier = Modifier.fillMaxWidth()
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
                            text = stringResource(R.string.dark_theme),
                            style =
                                MaterialTheme
                                    .typography
                                    .titleMedium
                        )

                        Text(
                            text =
                                stringResource(R.string.enable_dark_mode),
                            style =
                                MaterialTheme
                                    .typography
                                    .bodyMedium
                        )

                    }

                    Switch(
                        checked =
                            state.value.isDarkTheme,
                        onCheckedChange = {

                            viewModel.onEvent(
                                SettingsEvent.ThemeToggled
                            )

                        }
                    )

                }

            }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .padding(Dimens.MD),
                    verticalArrangement =
                        Arrangement.spacedBy(
                            Dimens.SM
                        )
                ) {

                    Text(
                        text = stringResource(R.string.data_management),
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Text(
                        text =
                            stringResource(R.string.delete_all_saved_transactions),
                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                MaterialTheme.colorScheme.error,
                            contentColor =
                                MaterialTheme.colorScheme.onError
                        ),
                        onClick = {
                            showDialog = true
                        }
                    ) {

                        Text(stringResource(R.string.clear_data))

                    }

                }

            }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .padding(Dimens.MD),
                    verticalArrangement =
                        Arrangement.spacedBy(
                            Dimens.XS
                        )
                ) {

                    Text(
                        text =
                            stringResource(R.string.app_name),
                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )

                    Text(
                        text = stringResource(R.string.version_app) + " " + viewModel.uiState.collectAsState().value.appVersion,
                        style =
                            MaterialTheme
                                .typography
                                .bodyMedium
                    )

                }

            }

        }

    }

    if (showDialog) {

        ConfirmationDialog(
            title = stringResource(R.string.clear_data),
            message =
                stringResource(R.string.delete_all_transactions),
            onConfirm = {

                viewModel.onEvent(
                    SettingsEvent.ClearData
                )

                showDialog = false

            },
            onDismiss = {

                showDialog = false

            }
        )

    }

}