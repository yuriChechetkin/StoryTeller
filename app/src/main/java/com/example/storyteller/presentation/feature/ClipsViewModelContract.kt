package com.example.storyteller.presentation.feature

import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.domain.entities.ClipItems
import com.example.storyteller.presentation.core.BaseViewModel
import com.example.storyteller.presentation.core.Data

internal interface ClipsViewModelContract {

    interface ViewModelApi : BaseViewModel<DomainState, UiState> {

        fun fetchClips()

        fun loadMoreClips(lastClipId: String)

        fun likeClip(clipId: String)

        fun shareClip(clip: Clip)
    }


    data class DomainState(
        val clips: Data<ClipItems>
    ) : BaseViewModel.DomainState

    data class UiState(
        val viewState: ViewState
    ) : BaseViewModel.UiState
}