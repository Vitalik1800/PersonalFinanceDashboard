package com.vs18.personalfinancedashboard.data.preferences

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.vs18.personalfinancedashboard.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "settings"
)

private const val DARK_THEME = "dark_theme"

class ThemePreferences(
    private val context: Context
) {


    companion object {

        @SuppressLint("SupportAnnotationUsage")
        @StringRes
        private val DARK_THEME_KEY =
            booleanPreferencesKey(
                DARK_THEME
            )

    }

    val isDarkTheme: Flow<Boolean> =
        context.dataStore.data.map { preferences ->

            preferences[
                    DARK_THEME_KEY
            ] ?: false

        }

    suspend fun saveTheme(
        isDarkTheme: Boolean
    ) {

        context.dataStore.edit { preferences ->

            preferences[
                    DARK_THEME_KEY
            ] = isDarkTheme

        }

    }

}