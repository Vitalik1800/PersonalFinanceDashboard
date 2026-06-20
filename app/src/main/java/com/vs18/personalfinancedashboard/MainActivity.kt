package com.vs18.personalfinancedashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vs18.personalfinancedashboard.ui.PersonalFinanceApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            PersonalFinanceApp()

        }
    }
}

