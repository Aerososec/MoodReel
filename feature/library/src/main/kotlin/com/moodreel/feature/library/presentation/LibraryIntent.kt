package com.moodreel.feature.library.presentation

import com.moodreel.core.model.LibraryStatus

sealed interface LibraryIntent {
    data class TabSelected(val status: LibraryStatus) : LibraryIntent
}
