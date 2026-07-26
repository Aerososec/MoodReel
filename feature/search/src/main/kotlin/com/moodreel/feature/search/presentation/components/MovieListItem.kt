package com.moodreel.feature.search.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.moodreel.core.designsystem.theme.MoodReelTheme
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import com.moodreel.core.model.Movie
import com.moodreel.core.ui.card.MoodReelCard

@Composable
fun MovieListItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    MoodReelCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.md)
        ) {
            MoviePoster(
                posterUrl = movie.posterUrl,
                mediaType = movie.mediaType,
                modifier = modifier
            )
            MovieInfo(
                movie = movie,
                modifier = modifier
            )
        }
    }
}

@Composable
fun MoviePoster(
    posterUrl: String?,
    mediaType: MediaType,
    modifier: Modifier
) {
    val shape = RoundedCornerShape(MoodReelTheme.radius.sm)

    if (!posterUrl.isNullOrBlank()) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            modifier = Modifier
                .size(width = 80.dp, height = 120.dp)
                .clip(shape),
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = modifier
                .size(width = 80.dp, height = 120.dp)
                .clip(shape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = mediaTypeIcon(mediaType),
                contentDescription = null,
                tint = MoodReelTheme.colors.onSurfaceVariant,
                modifier = Modifier.size(40.dp),
            )
        }
    }
}

private fun mediaTypeIcon(mediaType: MediaType) = when (mediaType) {
    MediaType.MOVIE -> Icons.Outlined.Movie
    MediaType.TV_SERIES -> Icons.Outlined.Tv
}

@Composable
private fun MovieInfo(movie: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.xs),
    ) {
        Text(
            text = movie.title,
            style = MoodReelTheme.typography.titleMedium,
            color = MoodReelTheme.colors.onSurface,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = buildSubtitle(movie),
            style = MoodReelTheme.typography.bodySmall,
            color = MoodReelTheme.colors.onSurfaceVariant,
        )
        if (movie.rating != null) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.xs),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                    tint = MoodReelTheme.colors.tertiary,
                    modifier = Modifier.size(16.dp),
                )
                Text(
                    text = "%.1f".format(movie.rating),
                    style = MoodReelTheme.typography.bodySmall,
                    color = MoodReelTheme.colors.onSurface,
                )
            }
        }
        if (movie.overview.isNotBlank()) {
            Text(
                text = movie.overview,
                style = MoodReelTheme.typography.bodySmall,
                color = MoodReelTheme.colors.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = MoodReelTheme.spacing.xs),
            )
        }
    }
}

private fun buildSubtitle(movie: Movie): String = buildString {
    append(if (movie.mediaType == MediaType.MOVIE) "Movie" else "TV Series")
    if (movie.releaseYear != null) {
        append(" · ${movie.releaseYear}")
    }
}

@Preview
@Composable
private fun MovieListItemPreview() {
    MoodReelTheme {
        MovieListItem(
            movie = Movie(
                id = MediaId(1),
                title = "Inception",
                mediaType = MediaType.MOVIE,
                posterUrl = null,
                releaseYear = 2010,
                rating = 8.4,
                overview = "A thief who steals corporate secrets through dream-sharing technology.",
            ),
            onClick = {},
        )
    }
}
