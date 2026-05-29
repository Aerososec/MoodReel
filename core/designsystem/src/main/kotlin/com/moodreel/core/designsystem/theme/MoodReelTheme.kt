package com.moodreel.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import com.moodreel.core.designsystem.color.EmotionColors
import com.moodreel.core.designsystem.color.LocalEmotionColors
import com.moodreel.core.designsystem.color.MoodReelDarkColorScheme
import com.moodreel.core.designsystem.color.MoodReelEmotionColorsDark
import com.moodreel.core.designsystem.color.MoodReelEmotionColorsLight
import com.moodreel.core.designsystem.color.MoodReelLightColorScheme
import com.moodreel.core.designsystem.dimens.LocalMoodReelRadius
import com.moodreel.core.designsystem.dimens.LocalMoodReelSpacing
import com.moodreel.core.designsystem.dimens.MoodReelRadius
import com.moodreel.core.designsystem.dimens.MoodReelSpacing
import com.moodreel.core.designsystem.typography.MoodReelTypography

@Composable
fun MoodReelTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> MoodReelDarkColorScheme
        else -> MoodReelLightColorScheme
    }

    val emotionColors = if (darkTheme) {
        MoodReelEmotionColorsDark
    } else {
        MoodReelEmotionColorsLight
    }

    CompositionLocalProvider(
        LocalEmotionColors provides emotionColors,
        LocalMoodReelSpacing provides MoodReelSpacing(),
        LocalMoodReelRadius provides MoodReelRadius(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = MoodReelTypography,
            content = content,
        )
    }
}

object MoodReelTheme {

    val colors: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val emotionColors: EmotionColors
        @Composable
        @ReadOnlyComposable
        get() = LocalEmotionColors.current

    val spacing: MoodReelSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalMoodReelSpacing.current

    val radius: MoodReelRadius
        @Composable
        @ReadOnlyComposable
        get() = LocalMoodReelRadius.current
}
