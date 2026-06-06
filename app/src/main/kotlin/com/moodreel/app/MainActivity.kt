package com.moodreel.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moodreel.core.designsystem.theme.MoodReelTheme
import com.moodreel.core.ui.button.MoodReelButton
import com.moodreel.core.ui.button.MoodReelTextButton
import com.moodreel.core.ui.card.MoodReelCard
import com.moodreel.core.ui.chip.MoodReelEmotionChip
import com.moodreel.core.ui.input.MoodReelTextField
import com.moodreel.core.ui.state.MoodReelEmptyState
import com.moodreel.core.ui.topbar.MoodReelTopBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodReelTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        MoodReelTopBar(title = "MoodReel")
                    },
                ) { innerPadding ->
                    Showcase(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Suppress("LongMethod")
@Composable
private fun Showcase(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedEmotion by remember { mutableStateOf("Cosy") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(MoodReelTheme.spacing.lg),
        verticalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.lg),
    ) {
        Text(
            text = "Дизайн-система",
            style = MoodReelTheme.typography.headlineMedium,
            color = MoodReelTheme.colors.onSurface,
        )

        MoodReelTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = "Поиск фильмов",
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        MoodReelCard(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Inception",
                style = MoodReelTheme.typography.titleLarge,
                color = MoodReelTheme.colors.onSurface,
            )
            Text(
                text = "Кристофер Нолан, 2010",
                style = MoodReelTheme.typography.bodyMedium,
                color = MoodReelTheme.colors.onSurfaceVariant,
                modifier = Modifier.padding(top = MoodReelTheme.spacing.xs),
            )
        }

        Text(
            text = "Эмоциональные теги",
            style = MoodReelTheme.typography.titleMedium,
            color = MoodReelTheme.colors.onSurface,
        )

        Column(verticalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.sm)) {
            EmotionRow(
                emotions = listOf(
                    "Cosy" to MoodReelTheme.emotionColors.cosy,
                    "Cathartic" to MoodReelTheme.emotionColors.cathartic,
                    "Mind-bending" to MoodReelTheme.emotionColors.mindBending,
                ),
                selectedEmotion = selectedEmotion,
                onEmotionClick = { selectedEmotion = it },
            )
            EmotionRow(
                emotions = listOf(
                    "Melancholic" to MoodReelTheme.emotionColors.melancholic,
                    "Unhinged" to MoodReelTheme.emotionColors.unhinged,
                    "Nostalgic" to MoodReelTheme.emotionColors.nostalgic,
                ),
                selectedEmotion = selectedEmotion,
                onEmotionClick = { selectedEmotion = it },
            )
        }

        MoodReelButton(
            text = "Сохранить",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )

        MoodReelTextButton(
            text = "Отмена",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = "Пример пустого состояния",
            style = MoodReelTheme.typography.titleMedium,
            color = MoodReelTheme.colors.onSurface,
        )

        MoodReelCard(modifier = Modifier.fillMaxWidth()) {
            MoodReelEmptyState(
                icon = Icons.Outlined.SearchOff,
                title = "Ничего не найдено",
                description = "Попробуй изменить запрос",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MoodReelTheme.spacing.xl),
            )
        }
    }
}

@Composable
private fun EmotionRow(
    emotions: List<Pair<String, androidx.compose.ui.graphics.Color>>,
    selectedEmotion: String,
    onEmotionClick: (String) -> Unit,
) {
    androidx.compose.foundation.layout.Row(
        horizontalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.sm),
    ) {
        emotions.forEach { (label, color) ->
            MoodReelEmotionChip(
                label = label,
                color = color,
                selected = selectedEmotion == label,
                onClick = { onEmotionClick(label) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShowcasePreview() {
    MoodReelTheme {
        Showcase()
    }
}
