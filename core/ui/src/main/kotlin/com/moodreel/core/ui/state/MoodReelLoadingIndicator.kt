package com.moodreel.core.ui.state

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moodreel.core.designsystem.theme.MoodReelTheme

@Composable
fun MoodReelLoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            color = MoodReelTheme.colors.primary,
            trackColor = MoodReelTheme.colors.surfaceContainerHigh,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodReelLoadingIndicatorPreview() {
    MoodReelTheme {
        MoodReelLoadingIndicator()
    }
}
