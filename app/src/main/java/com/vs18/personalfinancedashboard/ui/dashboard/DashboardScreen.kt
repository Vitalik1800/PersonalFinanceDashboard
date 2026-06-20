package com.vs18.personalfinancedashboard.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.vs18.personalfinancedashboard.R
import com.vs18.personalfinancedashboard.ui.components.BalanceCard
import com.vs18.personalfinancedashboard.ui.components.EmptyStateView
import com.vs18.personalfinancedashboard.ui.components.TransactionItem
import com.vs18.personalfinancedashboard.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onTransactionClick: (Int) -> Unit,
    viewModel: DashboardViewModel =
        koinViewModel()
) {

    val state =
        viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.dashboard))
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimens.MD),
            verticalArrangement =
                Arrangement.spacedBy(Dimens.MD)
        ) {

            item {

                BalanceCard(
                    title = stringResource(R.string.balance),
                    amount = state.value.balance
                )

            }

            item {

                BalanceCard(
                    title = stringResource(R.string.income),
                    amount = state.value.totalIncome
                )

            }

            item {

                BalanceCard(
                    title = stringResource(R.string.expenses),
                    amount = state.value.totalExpenses
                )

            }

            item {

                HorizontalDivider()

                Text(
                    text = stringResource(R.string.latest_transactions),
                    style =
                        MaterialTheme
                            .typography
                            .titleLarge,
                    modifier =
                        Modifier.padding(
                            top = Dimens.SM
                        )
                )

            }

            when {
                state.value.isLoading -> {

                    item {
                        CircularProgressIndicator()
                    }

                }

                state.value.latestTransactions.isEmpty() -> {

                    item {
                        EmptyStateView()
                    }

                }

                else -> {

                    items(
                        state.value.latestTransactions
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