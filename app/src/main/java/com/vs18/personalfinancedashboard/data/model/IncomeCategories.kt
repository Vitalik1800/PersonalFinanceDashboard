package com.vs18.personalfinancedashboard.data.model

import com.vs18.personalfinancedashboard.R

object IncomeCategories {

    val values = listOf(
        Category("salary", R.string.salary),
        Category("freelance", R.string.freelance),
        Category("investment", R.string.investment),
        Category("gift", R.string.gift),
        Category("other", R.string.other)
    )
}