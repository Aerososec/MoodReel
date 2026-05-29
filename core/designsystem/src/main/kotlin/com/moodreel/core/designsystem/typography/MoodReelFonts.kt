package com.moodreel.core.designsystem.typography

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.moodreel.core.designsystem.R

private val googleFontProvider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs,
)

internal val FrauncesFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Fraunces"),
        fontProvider = googleFontProvider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    ),
    Font(
        googleFont = GoogleFont("Fraunces"),
        fontProvider = googleFontProvider,
        weight = FontWeight.Medium,
        style = FontStyle.Normal,
    ),
    Font(
        googleFont = GoogleFont("Fraunces"),
        fontProvider = googleFontProvider,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal,
    ),
    Font(
        googleFont = GoogleFont("Fraunces"),
        fontProvider = googleFontProvider,
        weight = FontWeight.Bold,
        style = FontStyle.Normal,
    ),
)

internal val InterFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Inter"),
        fontProvider = googleFontProvider,
        weight = FontWeight.Normal,
    ),
    Font(
        googleFont = GoogleFont("Inter"),
        fontProvider = googleFontProvider,
        weight = FontWeight.Medium,
    ),
    Font(
        googleFont = GoogleFont("Inter"),
        fontProvider = googleFontProvider,
        weight = FontWeight.SemiBold,
    ),
    Font(
        googleFont = GoogleFont("Inter"),
        fontProvider = googleFontProvider,
        weight = FontWeight.Bold,
    ),
)
