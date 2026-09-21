package com.moodreel.feature.details.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import coil3.compose.AsyncImage
import com.moodreel.core.designsystem.theme.MoodReelTheme
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MovieDetails

@Composable
internal fun DetailsBody(
    details: MovieDetails,
    libraryStatus: LibraryStatus?,
    onStatusSelected: (LibraryStatus) -> Unit,
    onRemoveFromLibrary: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        AsyncImage(
            model = details.backdropUrl ?: details.posterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
        )

        Column(
            modifier = Modifier.padding(MoodReelTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.md),
        ) {
            Text(
                text = details.title,
                style = MoodReelTheme.typography.headlineMedium,
                color = MoodReelTheme.colors.onSurface,
            )

            if (details.originalTitle != details.title) {
                Text(
                    text = details.originalTitle,
                    style = MoodReelTheme.typography.bodyMedium,
                    color = MoodReelTheme.colors.onSurfaceVariant,
                )
            }

            details.tagline?.takeIf { it.isNotBlank() }?.let { tagline ->
                Text(
                    text = tagline,
                    style = MoodReelTheme.typography.bodyMedium,
                    fontStyle = FontStyle.Italic,
                    color = MoodReelTheme.colors.onSurfaceVariant,
                )
            }

            MetaRow(details = details)

            LibraryStatusSelector(
                currentStatus = libraryStatus,
                onStatusSelected = onStatusSelected,
                onRemove = onRemoveFromLibrary
            )

            AdditionalInformation(details)
        }
    }
}

@Composable
fun AdditionalInformation(details: MovieDetails) {
    if (details.genres.isNotEmpty()) {
        GenreRow(genres = details.genres)
    }

    if (details.overview.isNotBlank()) {
        Text(
            text = details.overview,
            style = MoodReelTheme.typography.bodyLarge,
            color = MoodReelTheme.colors.onSurface,
        )
    }

    if (details.productionCountries.isNotEmpty()) {
        Text(
            text = details.productionCountries.joinToString(),
            style = MoodReelTheme.typography.bodySmall,
            color = MoodReelTheme.colors.onSurfaceVariant,
        )
    }
}
