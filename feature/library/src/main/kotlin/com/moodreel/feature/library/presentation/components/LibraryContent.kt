package com.moodreel.feature.library.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.moodreel.core.designsystem.theme.MoodReelTheme
import com.moodreel.core.model.LibraryItem
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import com.moodreel.core.ui.state.MoodReelEmptyState
import com.moodreel.core.ui.state.MoodReelLoadingIndicator
import com.moodreel.feature.library.presentation.LibraryUiState

@Composable
internal fun LibraryContent(
    state: LibraryUiState,
    onItemClick: (MediaId, MediaType) -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        state.isLoading -> {
            MoodReelLoadingIndicator(modifier)
        }

        state.visibleItems.isEmpty() -> LibraryEmptyState(
            status = state.selectedStatus,
            modifier = modifier,
        )

        else -> LibraryList(
            libraryItems = state.visibleItems,
            onItemClick = onItemClick,
            modifier = modifier,
        )
    }
}

@Composable
private fun LibraryList(
    libraryItems: List<LibraryItem>,
    onItemClick: (MediaId, MediaType) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        contentPadding = PaddingValues(MoodReelTheme.spacing.sm)
    ) {
        items(
            items = libraryItems,
            key = { item -> "${item.mediaId.value}_${item.mediaType}" }
        ) {
            LibraryItemCard(
                item = it,
                onClick = { onItemClick(it.mediaId, it.mediaType) }
            )
        }
    }
}

@Composable
private fun LibraryEmptyState(
    status: LibraryStatus,
    modifier: Modifier = Modifier
) {
    when (status) {
        LibraryStatus.PLANNED -> {
            MoodReelEmptyState(
                icon = Icons.Outlined.BookmarkBorder,
                title = "Nothing planned yet",
                description = "Mark movies as \"Want to watch\" on their details page",
                modifier = modifier,
            )
        }

        LibraryStatus.WATCHED -> {
            MoodReelEmptyState(
                icon = Icons.Outlined.Visibility,
                title = "Nothing watched yet",
                description = "Movies you mark as watched will appear here",
                modifier = modifier,
            )
        }
    }
}
