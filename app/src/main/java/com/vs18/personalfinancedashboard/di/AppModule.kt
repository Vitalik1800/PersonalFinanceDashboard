package com.vs18.personalfinancedashboard.di

import androidx.room.Room
import com.vs18.personalfinancedashboard.data.db.FinanceDatabase
import com.vs18.personalfinancedashboard.data.preferences.ThemePreferences
import com.vs18.personalfinancedashboard.data.repository.TransactionRepository
import com.vs18.personalfinancedashboard.ui.dashboard.DashboardViewModel
import com.vs18.personalfinancedashboard.ui.settings.SettingsViewModel
import com.vs18.personalfinancedashboard.ui.statistics.StatisticsViewModel
import com.vs18.personalfinancedashboard.ui.transaction.TransactionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {

        Room.databaseBuilder(
            get(),
            FinanceDatabase::class.java,
            "finance_db"
        ).build()

    }

    single {
        get<FinanceDatabase>()
            .transactionDao()
    }

    single {
        TransactionRepository(get())
    }

    viewModel {
        DashboardViewModel(get())
    }

    viewModel {
        TransactionViewModel(get())
    }

    viewModel {
        StatisticsViewModel(get())
    }

    viewModel {
        SettingsViewModel(get(), get())
    }

    single {
        ThemePreferences(
            get()
        )
    }

}