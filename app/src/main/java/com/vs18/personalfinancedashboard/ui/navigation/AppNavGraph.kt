package com.vs18.personalfinancedashboard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vs18.personalfinancedashboard.ui.dashboard.DashboardScreen
import com.vs18.personalfinancedashboard.ui.settings.SettingsScreen
import com.vs18.personalfinancedashboard.ui.statistics.StatisticsScreen
import com.vs18.personalfinancedashboard.ui.transaction.AddTransactionScreen
import com.vs18.personalfinancedashboard.ui.transaction.TransactionDetailsScreen
import com.vs18.personalfinancedashboard.ui.transaction.TransactionsScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route,
        modifier = modifier
    ) {

        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onTransactionClick = { id ->

                    navController.navigate(
                        Routes.transactionDetails(id)
                    )

                }
            )
        }

        composable(Screen.Transactions.route) {
            TransactionsScreen(
                onAddTransaction = {
                    navController.navigate(
                        Screen.AddTransaction.route
                    )
                },
                onTransactionClick = { id ->

                    navController.navigate(
                        Routes.transactionDetails(id)
                    )

                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.Statistics.route) {
            StatisticsScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable(Screen.Settings.route) {
            SettingsScreen(onBack = {
                navController.popBackStack()
            })
        }

        composable(Screen.AddTransaction.route) {
            AddTransactionScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Screen.TransactionDetails.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val id =
                backStackEntry
                    .arguments
                    ?.getInt("id")
                    ?: return@composable

            TransactionDetailsScreen(
                transactionId = id,
                onBack = {
                    navController.popBackStack()
                }
            )

        }

    }

}