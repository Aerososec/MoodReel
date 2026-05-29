package com.moodreel.core.designsystem.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class EmotionColors(
    val cosy: Color,
    val cathartic: Color,
    val mindBending: Color,
    val melancholic: Color,
    val unhinged: Color,
    val nostalgic: Color,
    val adrenaline: Color,
    val tender: Color,
    val meditative: Color,
    val existential: Color,
    val wonder: Color,
    val dread: Color,
)

internal val MoodReelEmotionColorsDark = EmotionColors(
    cosy = Color(0xFFC9A876), // тёплый кофейный
    cathartic = Color(0xFFE08AA8), // приглушённый розовый
    mindBending = Color(0xFF8B9CE8), // электрический синий
    melancholic = Color(0xFFA8B8C9), // туманно-голубой
    unhinged = Color(0xFFFF7B5C), // огненный оранжевый
    nostalgic = Color(0xFFE8C49C), // выцветший охра
    adrenaline = Color(0xFFFF5C7B), // ярко-красный
    tender = Color(0xFFF4B8C9), // нежно-розовый
    meditative = Color(0xFF9CC9B8), // мятный
    existential = Color(0xFFB89CC9), // лавандовый
    wonder = Color(0xFFFFD876), // золотистый
    dread = Color(0xFF6B5C7B), // тёмно-пурпурный
)

internal val MoodReelEmotionColorsLight = EmotionColors(
    cosy = Color(0xFF8B6F47),
    cathartic = Color(0xFFC95B7B),
    mindBending = Color(0xFF5B6CC9),
    melancholic = Color(0xFF7B8FA1),
    unhinged = Color(0xFFE55934),
    nostalgic = Color(0xFFD4A574),
    adrenaline = Color(0xFFE5345C),
    tender = Color(0xFFE5879C),
    meditative = Color(0xFF7BB8A1),
    existential = Color(0xFF9C7BB8),
    wonder = Color(0xFFE5B834),
    dread = Color(0xFF4B3A5C),
)

val LocalEmotionColors = staticCompositionLocalOf<EmotionColors> {
    error("EmotionColors not provided. Wrap your content in MoodReelTheme {}.")
}
