package com.example.storyteller.presentation.feature

import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.domain.entities.ClipItems
import com.example.storyteller.presentation.core.Data
import com.example.storyteller.presentation.core.StateMapper
import com.example.storyteller.presentation.feature.ClipsViewModelContract.DomainState
import com.example.storyteller.presentation.feature.ClipsViewModelContract.UiState
import javax.inject.Inject

internal class ClipsStateMapper @Inject constructor() : StateMapper<DomainState, UiState> {

    override fun mapState(domainState: DomainState): UiState {
        return domainState.clips.mapDataState().run { UiState(this) }
    }

    private fun Data<ClipItems>.mapDataState(): ViewState {
        return when {
            content != null -> mapContentState(content.feedTitle, content.clips)
            loading -> ViewState.Loading
            error != null -> mapErrorState(error)
            else -> throw IllegalStateException("no content, no loading, no error state")
        }
    }

    private fun mapContentState(title: String, clips: List<Clip>): ViewState.Content {
        return ViewState.Content(
            title = title,
            clips = clips
        )
    }

    private fun mapErrorState(exception: Exception): ViewState.Error {
        return ViewState.Error(
            title = "Error",
            errorDescription = "${exception.message} error happened, click retry to load content",
            retryButtonText = "Retry"
        )
    }
}