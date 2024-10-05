package com.example.storyteller.domain.repositories

import com.example.storyteller.createClipsDomainSample
import com.example.storyteller.createClipsDtoSample
import com.example.storyteller.data.ClipsService
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.wheneverBlocking
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class ClipsRepositoryImplTest {

    private val service: ClipsService = mock()

    private val repository = ClipsRepositoryImpl(service)

    @Test
    fun `WHEN load clips executed THEN service load clip called`() = runTest {
        val clipsDto = createClipsDtoSample()
        val clipsExpected = createClipsDomainSample()

        wheneverBlocking { service.getClips() }.thenReturn(clipsDto)
        val actual = repository.loadClips()

        verify(service).getClips()
        assertEquals(clipsExpected, actual)
    }

    @Test
    fun `GIVEN response with error WHEN load clips executed THEN service load clip called and exception thrown`() = runTest {
        wheneverBlocking { service.getClips() }.thenAnswer { throw IllegalStateException("some exception") }

        val exception = assertFailsWith<IllegalStateException> {
            repository.loadClips()
        }

        Assert.assertEquals(exception.message, "some exception")

        verify(service).getClips()
    }

    @Test
    fun `WHEN load more clips executed THEN service load clip called`() = runTest {
        val clipsDto = createClipsDtoSample()
        val clipsExpected = createClipsDomainSample().clips

        wheneverBlocking { service.getClips() }.thenReturn(clipsDto)
        val actual = repository.loadMoreClips("anyId")

        verify(service).getClips()
        assertEquals(clipsExpected, actual)
    }

}