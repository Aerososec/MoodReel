package com.moodreel.core.designsystem.color

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

internal val MoodReelDarkColorScheme = darkColorScheme(
    primary = MoodReelColors.Primary80,
    onPrimary = MoodReelColors.Primary20,
    primaryContainer = MoodReelColors.Primary30,
    onPrimaryContainer = MoodReelColors.Primary90,

    secondary = MoodReelColors.Secondary80,
    onSecondary = MoodReelColors.Secondary20,
    secondaryContainer = MoodReelColors.Secondary30,
    onSecondaryContainer = MoodReelColors.Secondary90,

    tertiary = MoodReelColors.Tertiary80,
    onTertiary = MoodReelColors.Tertiary20,
    tertiaryContainer = MoodReelColors.Tertiary30,
    onTertiaryContainer = MoodReelColors.Tertiary90,

    background = MoodReelColors.SurfaceDark,
    onBackground = MoodReelColors.OnSurfaceDark,
    surface = MoodReelColors.SurfaceDark,
    onSurface = MoodReelColors.OnSurfaceDark,
    surfaceVariant = MoodReelColors.SurfaceContainerHighDark,
    onSurfaceVariant = MoodReelColors.OnSurfaceVariantDark,

    surfaceContainerLowest = MoodReelColors.SurfaceDark,
    surfaceContainerLow = MoodReelColors.SurfaceContainerLowDark,
    surfaceContainer = MoodReelColors.SurfaceContainerDark,
    surfaceContainerHigh = MoodReelColors.SurfaceContainerHighDark,
    surfaceContainerHighest = MoodReelColors.SurfaceContainerHighestDark,

    outline = MoodReelColors.OutlineDark,
    outlineVariant = MoodReelColors.OutlineVariantDark,

    error = MoodReelColors.ErrorDark,
    onError = MoodReelColors.OnErrorDark,
)

internal val MoodReelLightColorScheme = lightColorScheme(
    primary = MoodReelColors.Primary40,
    onPrimary = MoodReelColors.Primary95,
    primaryContainer = MoodReelColors.Primary90,
    onPrimaryContainer = MoodReelColors.Primary10,

    secondary = MoodReelColors.Secondary40,
    onSecondary = MoodReelColors.Secondary90,
    secondaryContainer = MoodReelColors.Secondary90,
    onSecondaryContainer = MoodReelColors.Secondary10,

    tertiary = MoodReelColors.Tertiary40,
    onTertiary = MoodReelColors.Tertiary90,
    tertiaryContainer = MoodReelColors.Tertiary90,
    onTertiaryContainer = MoodReelColors.Tertiary10,

    background = MoodReelColors.SurfaceLight,
    onBackground = MoodReelColors.OnSurfaceLight,
    surface = MoodReelColors.SurfaceLight,
    onSurface = MoodReelColors.OnSurfaceLight,
    surfaceVariant = MoodReelColors.SurfaceContainerHighLight,
    onSurfaceVariant = MoodReelColors.OnSurfaceVariantLight,

    surfaceContainerLowest = MoodReelColors.SurfaceLight,
    surfaceContainerLow = MoodReelColors.SurfaceContainerLowLight,
    surfaceContainer = MoodReelColors.SurfaceContainerLight,
    surfaceContainerHigh = MoodReelColors.SurfaceContainerHighLight,
    surfaceContainerHighest = MoodReelColors.SurfaceContainerHighestLight,

    outline = MoodReelColors.OutlineLight,
    outlineVariant = MoodReelColors.OutlineVariantLight,

    error = MoodReelColors.ErrorLight,
    onError = MoodReelColors.OnErrorLight,
)
