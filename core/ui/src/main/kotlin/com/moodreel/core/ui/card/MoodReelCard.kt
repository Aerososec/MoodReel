package com.moodreel.core.ui.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moodreel.core.designsystem.theme.MoodReelTheme

@Composable
fun MoodReelCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    contentPadding: PaddingValues = PaddingValues(MoodReelTheme.spacing.lg),
    content: @Composable () -> Unit,
) {
    val cardModifier = modifier
    val shape = RoundedCornerShape(MoodReelTheme.radius.lg)
    val colors = CardDefaults.cardColors(
        containerColor = MoodReelTheme.colors.surfaceContainer,
        contentColor = MoodReelTheme.colors.onSurface,
    )

    if (onClick != null) {
        Card(
            onClick = onClick,
            modifier = cardModifier,
            shape = shape,
            colors = colors,
        ) {
            Box(modifier = Modifier.padding(contentPadding)) {
                content()
            }
        }
    } else {
        Card(
            modifier = cardModifier,
            shape = shape,
            colors = colors,
        ) {
            Box(modifier = Modifier.padding(contentPadding)) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodReelCardPreview() {
    MoodReelTheme {
        MoodReelCard(modifier = Modifier.padding(MoodReelTheme.spacing.lg)) {
            Text(
                text = "Inception",
                style = MoodReelTheme.typography.titleLarge,
                color = MoodReelTheme.colors.onSurface,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodReelClickableCardPreview() {
    MoodReelTheme {
        MoodReelCard(
            modifier = Modifier.padding(MoodReelTheme.spacing.lg),
            onClick = {},
        ) {
            Text(
                text = "Кликни меня",
                style = MoodReelTheme.typography.titleMedium,
                color = MoodReelTheme.colors.onSurface,
            )
        }
    }
}
