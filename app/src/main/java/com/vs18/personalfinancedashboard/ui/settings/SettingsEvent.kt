package com.vs18.personalfinancedashboard.ui.settings

sealed interface SettingsEvent {

    data object ThemeToggled :
            SettingsEvent

    data object ClearData :
            SettingsEvent

}