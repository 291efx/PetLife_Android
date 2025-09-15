package com.erick.petlife.ui.theme

import android.os.Build
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

// Si quieres que Android 12+ use colores del sistema, pon true.
// Para que se vea EXACTO a iOS, déjalo en false.
private const val ENABLE_DYNAMIC_COLOR = false

private val LightColors = lightColorScheme(
    primary = PL_Primary,
    onPrimary = PL_OnPrimary,
    secondary = PL_Secondary,
    onSecondary = PL_OnSecondary,
    tertiary = PL_Tertiary,
    background = PL_Background,
    onBackground = PL_OnBackground,
    surface = PL_Surface,
    onSurface = PL_OnSurface,
    error = PL_Error,
    onError = PL_OnError
)

private val DarkColors = darkColorScheme(
    primary = PL_PrimaryDark,
    onPrimary = PL_OnPrimaryDark,
    secondary = PL_SecondaryDark,
    onSecondary = PL_OnSecondaryDark,
    tertiary = PL_TertiaryDark,
    background = PL_BackgroundDark,
    onBackground = PL_OnBackgroundDark,
    surface = PL_SurfaceDark,
    onSurface = PL_OnSurfaceDark,
    error = PL_ErrorDark,
    onError = PL_OnErrorDark
)

@Composable
fun PetLifeTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val ctx = LocalContext.current
    val colorScheme =
        if (ENABLE_DYNAMIC_COLOR && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (useDarkTheme) dynamicDarkColorScheme(ctx) else dynamicLightColorScheme(ctx)
        } else {
            if (useDarkTheme) DarkColors else LightColors
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // (tu Type.kt con estilo iOS)
        shapes = Shapes,
        content = content
    )
}
