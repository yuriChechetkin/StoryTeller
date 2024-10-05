package com.example.storyteller.domain.repositories

import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.domain.entities.ClipItems

interface ClipsRepository {

    suspend fun loadClips(): ClipItems

    suspend fun loadMoreClips(clipId: String): List<Clip>

    suspend fun likeClip(clipId: String)
}