package com.vs18.personalfinancedashboard.ui.navigation

object Routes {

    const val DASHBOARD = "dashboard"

    const val TRANSACTIONS = "transactions"

    const val STATISTICS = "statistics"

    const val SETTINGS = "settings"

    const val ADD_TRANSACTION =
        "add_transaction"

    const val TRANSACTION_DETAILS =
        "transaction_details/{id}"

    fun transactionDetails(
        id: Int
    ): String {

        return "transaction_details/$id"

    }

}