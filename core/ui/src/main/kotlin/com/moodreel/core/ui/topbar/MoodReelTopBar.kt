package com.moodreel.core.ui.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.moodreel.core.designsystem.theme.MoodReelTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodReelTopBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MoodReelTheme.typography.titleLarge,
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (navigationIcon != null && onNavigationClick != null) {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = "Назад",
                        tint = MoodReelTheme.colors.onSurface,
                    )
                }
            }
        },
        actions = actions,
        colors = topBarColors(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun topBarColors(): TopAppBarColors =
    TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MoodReelTheme.colors.surface,
        titleContentColor = MoodReelTheme.colors.onSurface,
        navigationIconContentColor = MoodReelTheme.colors.onSurface,
        actionIconContentColor = MoodReelTheme.colors.onSurface,
    )

@Preview(showBackground = true)
@Composable
private fun MoodReelTopBarPreview() {
    MoodReelTheme {
        MoodReelTopBar(
            title = "Поиск",
            navigationIcon = Icons.AutoMirrored.Outlined.ArrowBack,
            onNavigationClick = {},
        )
    }
}
