package com.moodreel.feature.library.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.moodreel.core.designsystem.theme.MoodReelTheme
import com.moodreel.core.model.LibraryItem
import com.moodreel.core.ui.card.MoodReelCard

@Composable
internal fun LibraryItemCard(
    item: LibraryItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    MoodReelCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = item.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(width = POSTER_WIDTH, height = POSTER_HEIGHT)
                    .clip(RoundedCornerShape(MoodReelTheme.radius.sm))
                    .background(MoodReelTheme.colors.surfaceVariant)
            )

            LibraryItemCardText(item)
        }
    }
}

@Composable
private fun LibraryItemCardText(
    item: LibraryItem
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.title,
            style = MoodReelTheme.typography.titleMedium,
            maxLines = 2
        )
        Text(
            text = item.mediaType.name,
            style = MoodReelTheme.typography.bodySmall,
            color = MoodReelTheme.colors.onSurfaceVariant
        )
        item.releaseYear?.let {
            Text(
                text = item.releaseYear.toString(),
                style = MoodReelTheme.typography.bodySmall,
                color = MoodReelTheme.colors.onSurfaceVariant
            )
        }
    }
}

private val POSTER_WIDTH = 60.dp
private val POSTER_HEIGHT = 90.dp
