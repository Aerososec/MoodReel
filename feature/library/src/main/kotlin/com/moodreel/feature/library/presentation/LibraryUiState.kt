package com.moodreel.feature.library.presentation

import com.moodreel.core.model.LibraryItem
import com.moodreel.core.model.LibraryStatus

data class LibraryUiState(
    val selectedStatus: LibraryStatus = LibraryStatus.PLANNED,
    val planned: List<LibraryItem> = emptyList(),
    val watched: List<LibraryItem> = emptyList(),
    val isLoading: Boolean = true
) {
    val visibleItems = when (selectedStatus) {
        LibraryStatus.PLANNED -> planned
        LibraryStatus.WATCHED -> watched
    }
}
