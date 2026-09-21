package com.moodreel.feature.details.presentation

import com.moodreel.core.model.LibraryStatus

sealed interface DetailsIntent {
    data object RetryClicked : DetailsIntent
    data class LibraryStatusSelected(val libraryStatus: LibraryStatus) : DetailsIntent
    data object RemoveFromLibraryClicked : DetailsIntent
}
