package com.example.stockcontrol.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Tema claro (fondo blanco y texto negro)
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    background = Color.White, // Fondo blanco
    surface = Color.White, // Superficie blanca
    onPrimary = Color.White, // Texto blanco sobre colores primarios
    onSecondary = Color.Black, // Texto negro sobre colores secundarios
    onBackground = Color.Black, // Texto negro sobre fondo blanco
    onSurface = Color.Black // Texto negro sobre superficie blanca
)

// Tema oscuro (fondo negro y texto blanco)
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    background = Color.Black, // Fondo negro
    surface = Color.Black, // Superficie negra
    onPrimary = Color.Black, // Texto negro sobre colores primarios
    onSecondary = Color.Black, // Texto negro sobre colores secundarios
    onBackground = Color.White, // Texto blanco sobre fondo negro
    onSurface = Color.White // Texto blanco sobre superficie negra
)

@Composable
fun StockControlTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
