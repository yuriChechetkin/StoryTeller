package com.example.storyteller.presentation.feature

import com.example.storyteller.createClipsDomainSample
import com.example.storyteller.presentation.core.Data
import org.junit.Test
import com.example.storyteller.presentation.feature.ClipsViewModelContract.DomainState
import com.example.storyteller.presentation.feature.ClipsViewModelContract.UiState
import kotlin.test.assertEquals

class ClipsStateMapperTest {

    private val stateMapper = ClipsStateMapper()

    @Test
    fun `GIVEN content domain state WHEN map to Ui state THEN correct state returned`() {
        val clips = createClipsDomainSample()
        val domainState = DomainState(
            clips = Data(
                content = clips
            )
        )

        val uiState = UiState(
            ViewState.Content(
                title = "feedTitle",
                clips = clips.clips
            )
        )

        assertEquals(uiState, stateMapper.mapState(domainState))
    }

    @Test
    fun `GIVEN loading domain state WHEN map to Ui state THEN correct state returned`() {
        val domainState = DomainState(
            clips = Data(
                loading = true
            )
        )

        val uiState = UiState(
            ViewState.Loading
        )

        assertEquals(uiState, stateMapper.mapState(domainState))
    }

    @Test
    fun `GIVEN error domain state WHEN map to Ui state THEN correct state returned`() {
        val domainState = DomainState(
            clips = Data(
                error = IllegalStateException("some exception")
            )
        )

        val uiState = UiState(
            ViewState.Error(
                title = "Error",
                errorDescription = "some exception error happened, click retry to load content",
                retryButtonText = "Retry"
            )
        )

        assertEquals(uiState, stateMapper.mapState(domainState))
    }

    @Test
    fun `GIVEN content-error-loading domain state WHEN map to Ui state THEN correct content state returned`() {
        val clips = createClipsDomainSample()
        val domainState = DomainState(
            clips = Data(
                content = clips,
                loading = true,
                error = IllegalStateException("some exception")
            )
        )

        val uiState = UiState(
            ViewState.Content(
                title = "feedTitle",
                clips = clips.clips
            )
        )

        assertEquals(uiState, stateMapper.mapState(domainState))
    }
}