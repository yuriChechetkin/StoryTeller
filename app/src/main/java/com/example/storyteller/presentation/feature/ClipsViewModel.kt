package com.example.storyteller.presentation.feature

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyteller.domain.repositories.ClipsRepository
import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.domain.providers.ShareProvider
import com.example.storyteller.presentation.core.Data
import com.example.storyteller.presentation.core.StateMapper
import com.example.storyteller.presentation.feature.ClipsViewModelContract.DomainState
import com.example.storyteller.presentation.feature.ClipsViewModelContract.UiState
import com.example.storyteller.presentation.feature.ClipsViewModelContract.ViewModelApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ClipsViewModel @Inject constructor(
    private val stateMapper: ClipsStateMapper,
    private val repository: ClipsRepository,
    private val shareProvider: ShareProvider
) : ViewModel(), ViewModelApi, StateMapper<DomainState, UiState> by stateMapper {

    @VisibleForTesting
    override val domainState by lazy {
        MutableStateFlow(
            DomainState(
                clips = Data(loading = true)
            )
        )
    }

    override val uiState: StateFlow<UiState> by lazy {
        domainState
            .map(::mapState)
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                mapState(domainState.value)
            )
    }

    override fun fetchClips() {
        viewModelScope.launch {
            try {
                val clipItems = repository.loadClips()
                domainState.update {
                    it.copy(clips = Data(clipItems))
                }
            } catch (exception: Exception) {
                domainState.update {
                    it.copy(clips = Data(error = exception))
                }
            }
        }
    }

    //stubbed load more fun
    override fun loadMoreClips(lastClipId: String) {
        viewModelScope.launch {
            val clipItems = repository.loadMoreClips(lastClipId)
            domainState.update {
                it.copy(
                    clips = Data(
                        content = it.clips.content?.copy(
                            clips = it.clips.content.clips + clipItems
                        )
                    )
                )
            }
        }
    }

    override fun likeClip(clipId: String) {
        viewModelScope.launch {
            repository.likeClip(clipId)
        }
    }

    override fun shareClip(clip: Clip) {
        shareProvider.shareClip(clip)
    }
}