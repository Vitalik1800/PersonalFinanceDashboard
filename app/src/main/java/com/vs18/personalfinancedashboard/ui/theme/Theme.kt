package com.vs18.personalfinancedashboard.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,

    background = BackgroundLight,
    surface = SurfaceLight,

    error = Error
)

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,

    background = BackgroundDark,
    surface = SurfaceDark,

    error = Error
)

@Composable
fun PersonalFinanceDashboardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme =
        if (darkTheme) {
            DarkColorScheme
        } else {
            LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}