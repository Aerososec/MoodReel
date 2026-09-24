package com.moodreel.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VideoLibrary
import androidx.compose.ui.graphics.vector.ImageVector

internal enum class MoodReelTab(
    val label: String,
    val icon: ImageVector
) {
    SEARCH("Search", Icons.Outlined.Search),
    LIBRARY("Library", Icons.Outlined.VideoLibrary)
}
