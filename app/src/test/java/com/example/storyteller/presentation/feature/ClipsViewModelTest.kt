package com.example.storyteller.presentation.feature

import app.cash.turbine.test
import com.example.storyteller.createClipsDomainSample
import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.domain.providers.ShareProvider
import com.example.storyteller.domain.repositories.ClipsRepository
import com.example.storyteller.presentation.core.Data
import com.example.storyteller.presentation.feature.ClipsViewModelContract.DomainState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.wheneverBlocking

internal class ClipsViewModelTest {

    private val stateMapper: ClipsStateMapper = mock()
    private val repository: ClipsRepository = mock()
    private val shareProvider: ShareProvider = mock()

    private val viewModel = ClipsViewModel(
        stateMapper = stateMapper,
        repository = repository,
        shareProvider = shareProvider
    )

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `WHEN fetch data called THEN repository invoked and domain state updated`() = runTest {
        val clips = createClipsDomainSample()
        wheneverBlocking { repository.loadClips() }.thenReturn(clips)
        viewModel.fetchClips()

        viewModel.domainState.test {
            assertEquals(awaitItem(), DomainState(Data(clips)))
        }

        verify(repository).loadClips()
    }

    @Test
    fun `WHEN like called THEN repository invoked`() = runTest {
        wheneverBlocking { repository.likeClip("clipId") }.thenReturn(Unit)
        viewModel.likeClip("clipId")

        verify(repository).likeClip("clipId")
    }

    @Test
    fun `WHEN share called THEN share provider invoked`() = runTest {
        val clip = Clip("id", "description", "url", "3", "4")
        viewModel.shareClip(clip)

        verify(shareProvider).shareClip(clip)
    }
}