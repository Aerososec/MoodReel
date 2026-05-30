package com.moodreel.core.ui.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moodreel.core.designsystem.theme.MoodReelTheme
import com.moodreel.core.ui.button.MoodReelButton

@Composable
fun MoodReelEmptyState(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(MoodReelTheme.spacing.xl),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MoodReelTheme.colors.onSurfaceVariant,
            modifier = Modifier.size(80.dp),
        )
        Text(
            text = title,
            style = MoodReelTheme.typography.titleLarge,
            color = MoodReelTheme.colors.onSurface,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = MoodReelTheme.spacing.lg),
        )
        Text(
            text = description,
            style = MoodReelTheme.typography.bodyMedium,
            color = MoodReelTheme.colors.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = MoodReelTheme.spacing.sm),
        )
        if (actionLabel != null && onActionClick != null) {
            MoodReelButton(
                text = actionLabel,
                onClick = onActionClick,
                modifier = Modifier.padding(top = MoodReelTheme.spacing.xl),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodReelEmptyStatePreview() {
    MoodReelTheme {
        MoodReelEmptyState(
            icon = Icons.Outlined.SearchOff,
            title = "Ничего не найдено",
            description = "Попробуй изменить запрос или поискать что-то другое",
            actionLabel = "Очистить поиск",
            onActionClick = {},
        )
    }
}
