package com.moodreel.feature.search.presentation

import com.moodreel.core.model.MediaId

sealed interface SearchSideEffect {
    data class NavigateToDetails(val id: MediaId) : SearchSideEffect
    data class ShowError(val errorMessage: String) : SearchSideEffect
}
