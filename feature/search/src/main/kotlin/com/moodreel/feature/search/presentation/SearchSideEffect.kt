package com.moodreel.feature.search.presentation

import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType

sealed interface SearchSideEffect {
    data class NavigateToDetails(val id: MediaId, val type: MediaType) : SearchSideEffect
    data class ShowError(val errorMessage: String) : SearchSideEffect
}
