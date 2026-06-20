package com.vs18.personalfinancedashboard.ui.navigation

sealed class Screen(
    val route: String
) {

    data object Dashboard :
            Screen(Routes.DASHBOARD)

    data object Transactions :
            Screen(Routes.TRANSACTIONS)

    data object Statistics :
            Screen(Routes.STATISTICS)

    data object Settings :
            Screen(Routes.SETTINGS)

    data object AddTransaction :
            Screen(Routes.ADD_TRANSACTION)

    data object TransactionDetails :
            Screen(Routes.TRANSACTION_DETAILS)

}