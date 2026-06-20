package com.vs18.personalfinancedashboard.data.model

import com.vs18.personalfinancedashboard.R

object ExpenseCategories {

    val values = listOf(
        Category("food", R.string.food),
        Category("transport", R.string.transport),
        Category("shopping", R.string.shopping),
        Category("entertainment", R.string.entertainment),
        Category("health", R.string.health),
        Category("education", R.string.education),
        Category("bills", R.string.bills),
        Category("travel", R.string.travel),
        Category("other", R.string.other)
    )
}