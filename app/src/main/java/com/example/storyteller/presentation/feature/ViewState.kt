package com.example.storyteller.presentation.feature

import com.example.storyteller.domain.entities.Clip

internal sealed interface ViewState {

    data object Loading : ViewState

    data class Content(
        val title: String,
        val clips: List<Clip>
    ) : ViewState

    data class Error(
        val title: String,
        val errorDescription: String,
        val retryButtonText: String
    ) : ViewState
}