package com.moodreel.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moodreel.core.designsystem.theme.MoodReelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodReelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ThemeShowcase(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun ThemeShowcase(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(MoodReelTheme.spacing.lg),
    ) {
        Text(
            text = "MoodReel",
            style = MoodReelTheme.typography.displayMedium,
            color = MoodReelTheme.colors.primary,
        )
        Text(
            text = "Late night at the cinema",
            style = MoodReelTheme.typography.titleMedium,
            color = MoodReelTheme.colors.onSurfaceVariant,
            modifier = Modifier.padding(top = MoodReelTheme.spacing.xs),
        )
        Text(
            text = "Дневник фильмов и сериалов с эмоциональной разметкой",
            style = MoodReelTheme.typography.bodyMedium,
            color = MoodReelTheme.colors.onSurface,
            modifier = Modifier.padding(top = MoodReelTheme.spacing.md),
        )

        // Демонстрация эмоциональных цветов
        Text(
            text = "Cosy",
            style = MoodReelTheme.typography.labelLarge,
            color = MoodReelTheme.emotionColors.cosy,
            modifier = Modifier.padding(top = MoodReelTheme.spacing.xl),
        )
        Text(
            text = "Cathartic",
            style = MoodReelTheme.typography.labelLarge,
            color = MoodReelTheme.emotionColors.cathartic,
        )
        Text(
            text = "Mind-bending",
            style = MoodReelTheme.typography.labelLarge,
            color = MoodReelTheme.emotionColors.mindBending,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ThemeShowcasePreview() {
    MoodReelTheme {
        ThemeShowcase()
    }
}
