package com.vs18.personalfinancedashboard.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.vs18.personalfinancedashboard.ui.navigation.AppNavGraph
import com.vs18.personalfinancedashboard.ui.navigation.BottomBar
import com.vs18.personalfinancedashboard.ui.settings.SettingsViewModel
import com.vs18.personalfinancedashboard.ui.theme.PersonalFinanceDashboardTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun PersonalFinanceApp() {

    val settingsViewModel: SettingsViewModel =
        koinViewModel()

    val settingsState =
        settingsViewModel
            .uiState
            .collectAsState()

    val navController =
        rememberNavController()

    PersonalFinanceDashboardTheme(
        darkTheme =
            settingsState.value.isDarkTheme
    ) {

        Scaffold(

            bottomBar = {

                BottomBar(
                    navController = navController
                )

            }

        ) { paddingValues ->

            AppNavGraph(
                navController = navController,
                modifier = Modifier.padding(
                    paddingValues
                )
            )

        }

    }

}