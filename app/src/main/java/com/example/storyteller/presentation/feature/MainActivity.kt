package com.example.storyteller.presentation.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.ui.theme.StoryTellerTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.storyteller.presentation.feature.ClipsViewModelContract.UiState
import com.example.storyteller.presentation.feature.ui.ClipsScreenContent
import com.example.storyteller.presentation.feature.ui.ClipsScreenError
import com.example.storyteller.presentation.feature.ui.ClipsScreenLoading


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ClipsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiState by viewModel.uiState.collectAsState()

            StoryTellerTheme {
                ClipsScreen(
                    uiState = uiState,
                    onLoadMore = viewModel::loadMoreClips,
                    likeClip = viewModel::likeClip,
                    shareClip = viewModel::shareClip,
                    retryLoading = viewModel::fetchClips,
                    modifier = Modifier
                )
            }
        }
        viewModel.fetchClips()
    }

    @Composable
    private fun ClipsScreen(
        uiState: UiState,
        onLoadMore: (String) -> Unit,
        likeClip: (String) -> Unit,
        shareClip: (Clip) -> Unit,
        retryLoading: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        when (val viewState = uiState.viewState) {
            is ViewState.Content -> ClipsScreenContent(
                viewState = viewState,
                onLoadMore = onLoadMore,
                likeClip = likeClip,
                shareClip = shareClip,
                modifier = modifier
            )

            is ViewState.Loading -> ClipsScreenLoading(viewState, modifier)
            is ViewState.Error -> ClipsScreenError(viewState, retryLoading, modifier)
        }
    }
}