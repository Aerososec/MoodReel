package com.moodreel.feature.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moodreel.core.common.error.AppException
import com.moodreel.core.domain.usecase.GetMediaDetailsUseCase
import com.moodreel.core.model.MediaId
import com.moodreel.core.model.MediaType
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModel.Factory::class)
class DetailsViewModel @AssistedInject constructor(
    val getMediaDetailsUseCase: GetMediaDetailsUseCase,
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
        }
    }

    private fun loadDetails() {
        job?.cancel()
        job = viewModelScope.launch {
            _uiState.value = DetailsUiState.Loading
            try {
                val content = getMediaDetailsUseCase(id = mediaId, type = mediaType)
                _uiState.value = DetailsUiState.Content(content = content)
            } catch (e: AppException) {
                _uiState.value = DetailsUiState.Error(e.toDetailsError())
            }
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
