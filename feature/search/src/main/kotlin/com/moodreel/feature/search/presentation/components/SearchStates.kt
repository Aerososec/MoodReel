package com.moodreel.feature.search.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudOff
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.SearchOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moodreel.core.ui.state.MoodReelEmptyState
import com.moodreel.feature.search.presentation.SearchError

object SearchStates {
    @Composable
    fun Initial(modifier: Modifier = Modifier) {
        MoodReelEmptyState(
            icon = Icons.Outlined.Search,
            title = "Search for movies and shows",
            description = "Type a title above to find movies and TV series",
            modifier = modifier
        )
    }

    @Composable
    fun NoResults(query: String, modifier: Modifier = Modifier) {
        MoodReelEmptyState(
            icon = Icons.Outlined.SearchOff,
            title = "Nothing found",
            description = "We couldn't find anything for \"$query\". Try a different title.",
            modifier = modifier
        )
    }

    @Composable
    fun Error(error: SearchError, onRetry: () -> Unit, modifier: Modifier = Modifier) {
        val (icon, title, description) = when (error) {
            SearchError.NoInternet -> Triple(
                Icons.Outlined.CloudOff,
                "No internet",
                "Check your connection and try again",
            )

            is SearchError.Server -> Triple(
                Icons.Outlined.ErrorOutline,
                "Server error",
                "Something went wrong on our side (${error.code}). Please try again.",
            )

            is SearchError.Unknown -> Triple(
                Icons.Outlined.ErrorOutline,
                "Something went wrong",
                error.errorMessage,
            )
        }

        MoodReelEmptyState(
            icon = icon,
            title = title,
            description = description,
            actionLabel = "Retry",
            onActionClick = onRetry,
            modifier = modifier,
        )
    }
}
