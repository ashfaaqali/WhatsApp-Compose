package com.ali.whatsappcompose.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF075E54), // WhatsApp's primary color
    secondary = Color(0xFF128C7E), // WhatsApp's secondary color
    tertiary = Color(0xFF25D366) // WhatsApp's tertiary color
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF075E54), // WhatsApp's primary color
    secondary = Color(0xFF128C7E), // WhatsApp's secondary color
    tertiary = Color(0xFF25D366) // WhatsApp's tertiary color

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


@Composable
fun WhatsappComposeTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val primaryColor = PrimaryColor

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val activity = (view.context as Activity)
            activity.window.statusBarColor = primaryColor.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}