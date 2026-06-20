package com.vs18.personalfinancedashboard.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.vs18.personalfinancedashboard.R

@Composable
fun BottomBar(
    navController: NavHostController
) {

    val items = listOf(
        Screen.Dashboard,
        Screen.Transactions,
        Screen.Statistics,
        Screen.Settings
    )

    val navBackStackEntry =
        navController.currentBackStackEntryAsState()

    val currentRoute =
        navBackStackEntry.value
            ?.destination
            ?.route

    NavigationBar {

        items.forEach { screen ->

            NavigationBarItem(
                selected =
                    currentRoute == screen.route,

                onClick = {

                    navController.navigate(
                        screen.route
                    ) {

                        popUpTo(
                            navController.graph.startDestinationId
                        )

                        launchSingleTop = true
                    }

                },

                icon = {

                    Icon(
                        imageVector =
                            when (screen) {

                                Screen.Dashboard ->
                                    Icons.Default.Home

                                Screen.Transactions ->
                                    Icons.Default.List

                                Screen.Statistics ->
                                    Icons.Default.BarChart

                                Screen.Settings ->
                                    Icons.Default.Settings

                                else ->
                                    Icons.Default.Home
                            },
                        contentDescription = null
                    )

                },

                label = {

                    Text(
                        text =
                            when (screen) {

                                Screen.Dashboard ->
                                    stringResource(R.string.dashboard)

                                Screen.Transactions ->
                                    stringResource(R.string.transactions)

                                Screen.Statistics ->
                                    stringResource(R.string.statistics)

                                Screen.Settings ->
                                    stringResource(R.string.settings)

                                else ->
                                    ""

                            }
                    )

                }

            )

        }

    }

}