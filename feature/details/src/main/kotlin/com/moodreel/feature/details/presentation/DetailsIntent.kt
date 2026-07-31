package com.moodreel.feature.details.presentation

sealed interface DetailsIntent {
    data object RetryClicked : DetailsIntent
}
