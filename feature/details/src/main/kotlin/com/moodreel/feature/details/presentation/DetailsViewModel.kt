package com.moodreel.feature.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodreel.core.common.error.AppException
import com.moodreel.core.domain.usecase.GetMediaDetailsUseCase
import com.moodreel.core.domain.usecase.ObserveLibraryStatusUseCase
import com.moodreel.core.domain.usecase.RemoveFromLibraryUseCase
import com.moodreel.core.domain.usecase.SaveToLibraryUseCase
import com.moodreel.core.model.LibraryStatus
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModel.Factory::class)
class DetailsViewModel @AssistedInject constructor(
    val getMediaDetailsUseCase: GetMediaDetailsUseCase,
    val observeLibraryStatusUseCase: ObserveLibraryStatusUseCase,
    val removeFromLibraryUseCase: RemoveFromLibraryUseCase,
    val saveToLibraryUseCase: SaveToLibraryUseCase,
    @Assisted val rawMediaId: Int,
    @Assisted val mediaType: MediaType
) : ViewModel() {
    private val mediaId = MediaId(rawMediaId)

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var job: Job? = null

    init {
        loadDetails()
    }

    fun handleIntent(intent: DetailsIntent) {
        when (intent) {
            DetailsIntent.RetryClicked -> {
                loadDetails()
            }
            is DetailsIntent.LibraryStatusSelected -> {
                saveToLibrary(intent.libraryStatus)
            }
            DetailsIntent.RemoveFromLibraryClicked -> {
                removeFromLibrary()
            }
        }
    }

    private fun loadDetails() {
        job?.cancel()
        _uiState.value = DetailsUiState.Loading
        job = combine(
            flow { emit(getMediaDetailsUseCase(mediaId, mediaType)) },
            observeLibraryStatusUseCase(mediaId, mediaType),
        ) { details, status ->
            DetailsUiState.Content(details, status)
        }
            .onEach { state -> _uiState.value = state }
            .catch { throwable ->
                if (throwable !is AppException) throw throwable
                _uiState.value = DetailsUiState.Error(throwable.toDetailsError())
            }
            .launchIn(viewModelScope)
    }

    private fun saveToLibrary(status: LibraryStatus) {
        val content = _uiState.value as? DetailsUiState.Content ?: return
        viewModelScope.launch {
            saveToLibraryUseCase(movieDetails = content.content, status = status)
        }
    }

    private fun removeFromLibrary() {
        viewModelScope.launch {
            removeFromLibraryUseCase(mediaId = mediaId, mediaType = mediaType)
        }
    }

    private fun AppException.toDetailsError(): DetailsError = when (this) {
        is AppException.NoInternet -> DetailsError.NoInternet
        is AppException.Server -> if (code == HTTP_NOT_FOUND) {
            DetailsError.NotFound
        } else {
            DetailsError.Server(code)
        }
        is AppException.Parsing -> DetailsError.Unknown(message.orEmpty())
        is AppException.Unknown -> DetailsError.Unknown(message.orEmpty())
    }

    @AssistedFactory
    interface Factory {
        fun create(mediaId: Int, mediaType: MediaType): DetailsViewModel
    }

    private companion object {
        const val HTTP_NOT_FOUND = 404
    }
}
