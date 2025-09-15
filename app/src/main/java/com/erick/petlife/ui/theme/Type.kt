package com.erick.petlife.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Si NO tienes aún SF Pro en res/font, dejamos la del sistema:
private val AppFontFamily = FontFamily.Default

/*  ────────────────────────────────────────────────────────────────────────────
    Si más adelante añades SF Pro (recomendado para igualar iOS):
    1) Copia a app/src/main/res/font/ estos archivos (nombres de ejemplo):
       - sf_pro_display_regular.ttf
       - sf_pro_display_semibold.ttf
       - sf_pro_text_regular.ttf
       - sf_pro_text_semibold.ttf
    2) Descomenta y ajusta el bloque abajo y comenta AppFontFamily = FontFamily.Default
*/
// import androidx.compose.ui.text.font.Font
// private val AppFontFamily = FontFamily(
//     Font(R.font.sf_pro_text_regular, FontWeight.Normal),
//     Font(R.font.sf_pro_text_semibold, FontWeight.SemiBold)
// )

/*
    Referencia tamaños iOS (pt) ≈ (sp) y lineHeights aproximados:
    - Large Title: 34 / 41
    - Title 1:    28 / 34
    - Title 2:    22 / 28
    - Title 3:    20 / 25
    - Headline:   17 / 22 (Semibold)
    - Body:       17 / 22 (Regular)
    - Callout:    16 / 21
    - Subhead:    15 / 20
    - Footnote:   13 / 18
    - Caption 1:  12 / 16
    - Caption 2:  11 / 13
*/

// Mapeo a roles de Material 3 manteniendo jerarquía visual de iOS.
val Typography = Typography(

    // iOS Large Title (34/41)
    displayLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp,
        lineHeight = 41.sp,
        letterSpacing = 0.sp
    ),

    // iOS Title 1 (28/34)
    headlineLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),

    // iOS Title 2 (22/28)
    headlineMedium = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    // iOS Title 3 (20/25)
    headlineSmall = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 25.sp,
        letterSpacing = 0.sp
    ),

    // iOS Headline (17/22, Semibold)
    titleLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),

    // iOS Body (17/22, Regular)
    bodyLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),

    // iOS Callout (16/21)
    titleMedium = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ),

    // iOS Subhead (15/20)
    bodyMedium = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    // iOS Footnote (13/18)
    bodySmall = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),

    // iOS Caption 1 (12/16)
    labelLarge = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),

    // iOS Caption 2 (11/13)
    labelSmall = TextStyle(
        fontFamily = AppFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.sp
    )

    // Otros roles (displayMedium, displaySmall, titleSmall, labelMedium)
    // quedan con defaults o puedes replicar alguno de los anteriores si los usas.
)
