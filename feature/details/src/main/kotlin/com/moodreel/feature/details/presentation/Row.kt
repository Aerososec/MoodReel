package com.moodreel.feature.details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moodreel.core.designsystem.theme.MoodReelTheme
import com.moodreel.core.model.Genre
import com.moodreel.core.model.MovieDetails

@Composable
internal fun MetaRow(
    details: MovieDetails,
    modifier: Modifier = Modifier,
) {
    val parts = buildList {
        details.releaseYear?.let { add(it.toString()) }
        details.runtimeMinutes?.let { add(formatRuntime(it)) }
        details.rating?.let { add("★ %.1f (%d)".format(it, details.voteCount)) }
    }

    if (parts.isNotEmpty()) {
        Text(
            text = parts.joinToString(separator = " · "),
            style = MoodReelTheme.typography.bodyMedium,
            color = MoodReelTheme.colors.onSurfaceVariant,
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun GenreRow(
    genres: List<Genre>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.sm),
        verticalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.sm),
    ) {
        genres.forEach { genre ->
            AssistChip(
                onClick = {},
                enabled = false,
                label = { Text(text = genre.name) },
            )
        }
    }
}

private fun formatRuntime(minutes: Int): String {
    val hours = minutes / MINUTES_IN_HOUR
    val rest = minutes % MINUTES_IN_HOUR
    return if (hours > 0) "$hours ч $rest мин" else "$rest мин"
}

private const val MINUTES_IN_HOUR = 60
