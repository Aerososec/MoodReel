package com.moodreel.feature.library.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodreel.core.domain.usecase.ObserveLibraryUseCase
import com.moodreel.core.model.LibraryStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor(
    private val observeLibraryUseCase: ObserveLibraryUseCase
) : ViewModel() {
    private val selectedStatus = MutableStateFlow(LibraryStatus.PLANNED)

    val uiState = combine(
        selectedStatus,
        observeLibraryUseCase(LibraryStatus.PLANNED),
        observeLibraryUseCase(LibraryStatus.WATCHED)
    ) { selected, planned, watched ->
        LibraryUiState(
            selectedStatus = selected,
            planned = planned,
            watched = watched,
            isLoading = false
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS),
            initialValue = LibraryUiState()
        )

    fun handleIntent(intent: LibraryIntent) {
        when (intent) {
            is LibraryIntent.TabSelected -> selectedStatus.value = intent.status
        }
    }

    private companion object {
        const val STOP_TIMEOUT_MS = 5_000L
    }
}
