package com.example.storyteller.domain.repositories

import com.example.storyteller.data.ClipsService
import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.domain.entities.ClipItems
import com.example.storyteller.domain.mappers.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClipsRepositoryImpl @Inject constructor(
    private val service: ClipsService
) : ClipsRepository {

    override suspend fun loadClips(): ClipItems {
        return withContext(Dispatchers.IO) {
            service.getClips().toDomain()
        }
    }

    override suspend fun loadMoreClips(clipId: String): List<Clip> {
        //TODO should be implemented some kinda of cursor pagination
        return withContext(Dispatchers.IO) {
            service.getClips().toDomain().clips
        }
    }

    override suspend fun likeClip(clipId: String) {
        return withContext(Dispatchers.IO) {
            //TODO("Not yet implemented: jira task reference")
        }
    }
}