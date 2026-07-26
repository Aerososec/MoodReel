package com.moodreel.feature.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodreel.core.common.error.AppException
import com.moodreel.core.domain.usecase.SearchMoviesUseCase
import com.moodreel.core.model.MediaId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffects = Channel<SearchSideEffect>(Channel.BUFFERED)
    val sideEffects = _sideEffects.receiveAsFlow()

    private var searchJob: Job? = null

    init {
        observeTitleChanged()
    }

    fun handleIntent(intent: SearchIntent) {
        when (intent) {
            SearchIntent.ClearTitle -> clearTitle()
            is SearchIntent.MovieClicked -> movieClicked(intent.id)
            SearchIntent.RetryClicked -> retryClicked()
            is SearchIntent.TitleChanged -> titleChanged(intent.title)
        }
    }

    private fun clearTitle() {
        searchJob?.cancel()
        _uiState.value = SearchUiState()
    }

    private fun movieClicked(id: MediaId) {
        viewModelScope.launch {
            _sideEffects.send(SearchSideEffect.NavigateToDetails(id))
        }
    }

    private fun retryClicked() {
        val currentTitle = _uiState.value.title
        if (currentTitle.isNotBlank()) {
            performSearch(currentTitle)
        }
    }

    private fun titleChanged(newTitle: String) {
        _uiState.update { it.copy(title = newTitle, error = null) }
    }

    @OptIn(FlowPreview::class)
    private fun observeTitleChanged() {
        _uiState
            .map { it.title }
            .distinctUntilChanged()
            .debounce(SEARCH_DEBOUNCE_MS)
            .filter { it.isNotBlank() }
            .onEach { performSearch(it) }
            .launchIn(viewModelScope)
    }

    private fun performSearch(title: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val result = searchMoviesUseCase(title)
                _uiState.update { it.copy(isLoading = false, results = result) }
            } catch (e: AppException) {
                handleException(e)
            }
        }
    }

    private suspend fun handleException(e: AppException) {
        val searchError = when (e) {
            is AppException.NoInternet -> SearchError.NoInternet
            is AppException.Parsing -> SearchError.Unknown(e.message ?: "Parsing error")
            is AppException.Server -> SearchError.Server(e.code)
            is AppException.Unknown -> SearchError.Unknown(e.message ?: "Unknown error")
        }

        _uiState.update { it.copy(error = searchError, isLoading = false, results = emptyList()) }
        _sideEffects.send(SearchSideEffect.ShowError(e.message ?: "Something went wrong"))
    }

    companion object {
        private const val SEARCH_DEBOUNCE_MS = 400L
    }
}
