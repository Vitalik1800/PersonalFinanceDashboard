package com.vs18.personalfinancedashboard.ui.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.vs18.personalfinancedashboard.ui.components.StatisticsCard
import com.vs18.personalfinancedashboard.ui.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(
    onBack: () -> Unit,
    viewModel: StatisticsViewModel =
        koinViewModel()
) {

    val state =
        viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.statistics))
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

        if (
            state.value.categoryStats.isEmpty() &&
            state.value.monthlyStats.isEmpty()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                EmptyStateView()

            }


        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Dimens.MD),
                verticalArrangement =
                    Arrangement.spacedBy(Dimens.MD)
            ) {

                item {

                    Text(
                        text = stringResource(R.string.overview),
                        style =
                            MaterialTheme
                                .typography
                                .titleLarge
                    )

                }

                item {

                    StatisticsCard(
                        title = stringResource(R.string.income),
                        value =
                            state.value.income.toString()
                    )
                }

                item {
                    StatisticsCard(
                        title = stringResource(R.string.expenses),
                        value =
                            state.value.expenses.toString()
                    )

                }


                if (
                    state.value
                        .categoryStats
                        .isNotEmpty()
                ) {

                    item {

                        Text(
                            text = stringResource(R.string.by_category),
                            style =
                                MaterialTheme
                                    .typography
                                    .titleLarge
                        )

                    }

                }

                items(
                    state.value.categoryStats.toList()
                ) { (category, amount) ->

                    StatisticsCard(
                        title = category,
                        value =
                            amount.toString()
                    )

                }

                if (
                    state.value
                        .monthlyStats
                        .isNotEmpty()
                ) {

                    item {

                        Text(
                            text = stringResource(R.string.by_month),
                            style =
                                MaterialTheme
                                    .typography
                                    .titleLarge
                        )

                    }

                }

                items(
                    state.value.monthlyStats.toList()
                ) { (month, amount) ->

                    StatisticsCard(
                        title = month,
                        value =
                            amount.toString()
                    )

                }


            }

        }
    }
}