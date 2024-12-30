package com.example.iclickipay.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = AppOrange,
    secondary = AppBlue,
    tertiary = AppGreen,
    surfaceVariant = AppOrangeLight,
    surface = AppOrangeLight
)

private val LightColorScheme = lightColorScheme(
    primary = AppOrange,
    secondary = AppBlue,
    tertiary = AppGreen,
    surfaceVariant = AppOrangeLight,
    surface = AppOrangeLight

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    ,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun IClickIPayTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val CustomShapes = Shapes(
        small = RoundedCornerShape(16.dp),  // Set the default small shape for buttons (rounded corners)
        medium = RoundedCornerShape(16.dp), // You can adjust medium or large shapes too
        large = RoundedCornerShape(16.dp)
    )

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
        shapes = CustomShapes,
        content = content
    )
}