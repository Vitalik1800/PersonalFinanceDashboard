package com.vs18.personalfinancedashboard.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R
import com.vs18.personalfinancedashboard.ui.components.EmptyStateView
import com.vs18.personalfinancedashboard.ui.components.FilterSelector
import com.vs18.personalfinancedashboard.ui.components.SortSelector
import com.vs18.personalfinancedashboard.ui.components.TransactionItem
import com.vs18.personalfinancedashboard.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsScreen(
    onAddTransaction: () -> Unit,
    onTransactionClick: (Int) -> Unit,
    onBack: () -> Unit,
    viewModel: TransactionViewModel =
        koinViewModel()
) {

    val state =
        viewModel.uiState.collectAsState()

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(stringResource(R.string.transactions))
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

        floatingActionButton = {

            FloatingActionButton(
                onClick = onAddTransaction,
                containerColor =
                    MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }

        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimens.MD),
            verticalArrangement =
                Arrangement.spacedBy(Dimens.MD)
        ) {

            FilterSelector(
                selected =
                    state.value.selectedFilter,
                onSelected = {

                    viewModel.onEvent(
                        TransactionEvent.FilterChanged(it)
                    )

                }
            )

            SortSelector(
                selected =
                    state.value.selectedSort,
                onSelected = {

                    viewModel.onEvent(
                        TransactionEvent.SortChanged(it)
                    )
                }
            )

            if (
                state.value.filteredTransactions.isEmpty()
            ) {

                EmptyStateView()

            } else {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement =
                        Arrangement.spacedBy(
                            Dimens.XS
                        )
                ) {

                    items(
                        state.value.filteredTransactions
                    ) { transaction ->

                        TransactionItem(
                            transaction = transaction,
                            onClick = onTransactionClick
                        )

                    }

                }


            }

        }

    }
}