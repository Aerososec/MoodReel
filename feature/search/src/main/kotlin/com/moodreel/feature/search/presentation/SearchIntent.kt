package com.moodreel.feature.search.presentation

import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType

sealed interface SearchIntent {
    data class TitleChanged(val title: String) : SearchIntent
    data object ClearTitle : SearchIntent
    data class MovieClicked(val id: MediaId, val type: MediaType) : SearchIntent
    data object RetryClicked : SearchIntent
}
