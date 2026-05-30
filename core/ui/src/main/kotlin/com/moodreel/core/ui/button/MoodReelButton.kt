package com.moodreel.core.ui.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.moodreel.core.designsystem.theme.MoodReelTheme

@Composable
fun MoodReelButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = RoundedCornerShape(MoodReelTheme.radius.md),
        contentPadding = PaddingValues(
            horizontal = MoodReelTheme.spacing.xl,
            vertical = MoodReelTheme.spacing.md,
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MoodReelTheme.colors.primary,
            contentColor = MoodReelTheme.colors.onPrimary,
        ),
    ) {
        if (leadingIcon != null) {
            androidx.compose.material3.Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                modifier = Modifier.size(MoodReelTheme.spacing.lg),
            )
            Spacer(modifier = Modifier.size(MoodReelTheme.spacing.sm))
        }
        Text(
            text = text,
            style = MoodReelTheme.typography.labelLarge,
        )
    }
}

@Composable
fun MoodReelTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    TextButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
    ) {
        Text(
            text = text,
            style = MoodReelTheme.typography.labelLarge,
            color = MoodReelTheme.colors.primary,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodReelButtonPreview() {
    MoodReelTheme {
        MoodReelButton(
            text = "Сохранить фильм",
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodReelTextButtonPreview() {
    MoodReelTheme {
        MoodReelTextButton(
            text = "Отмена",
            onClick = {},
        )
    }
}
