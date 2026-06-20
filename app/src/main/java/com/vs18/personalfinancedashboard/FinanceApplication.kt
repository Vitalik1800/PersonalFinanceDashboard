package com.vs18.personalfinancedashboard

import android.app.Application
import com.vs18.personalfinancedashboard.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FinanceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@FinanceApplication)

            modules(appModule)

        }

    }

}