package com.moodreel.core.ui.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moodreel.core.designsystem.theme.MoodReelTheme

@Composable
fun MoodReelEmotionChip(
    label: String,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (selected) color else Color.Transparent
    val contentColor = if (selected) MoodReelTheme.colors.onSurface else color
    val shape = RoundedCornerShape(MoodReelTheme.radius.full)
    val borderWidth = if (selected) 0.dp else 1.dp

    Row(
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .border(width = borderWidth, color = color, shape = shape)
            .clickable(onClick = onClick)
            .padding(
                PaddingValues(
                    horizontal = MoodReelTheme.spacing.md,
                    vertical = MoodReelTheme.spacing.sm,
                ),
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            text = label,
            style = MoodReelTheme.typography.labelMedium,
            color = contentColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodReelEmotionChipPreview() {
    MoodReelTheme {
        Row(
            modifier = Modifier.padding(MoodReelTheme.spacing.lg),
            horizontalArrangement = Arrangement.spacedBy(MoodReelTheme.spacing.sm),
        ) {
            MoodReelEmotionChip(
                label = "Cosy",
                color = MoodReelTheme.emotionColors.cosy,
                selected = true,
                onClick = {},
            )
            MoodReelEmotionChip(
                label = "Cathartic",
                color = MoodReelTheme.emotionColors.cathartic,
                selected = false,
                onClick = {},
            )
        }
    }
}
