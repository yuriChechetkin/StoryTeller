package com.example.storyteller

import com.example.storyteller.data.models.ClipDto
import com.example.storyteller.data.models.ClipItemsDto
import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.domain.entities.ClipItems

fun createClipsDtoSample() = ClipItemsDto(
    feedTitle = "feedTitle",
    clips = listOf(
        ClipDto(
            id = "id1",
            description = "description",
            url = "url1",
            shareCountDisplay = "1",
            likeCountDisplay = "2"
        ),
        ClipDto(
            id = "id2",
            description = null,
            url = "url2",
            shareCountDisplay = "3",
            likeCountDisplay = "4"
        )
    )
)

fun createClipsDomainSample() = ClipItems(
    feedTitle = "feedTitle",
    clips = listOf(
        Clip(
            id = "id1",
            description = "description",
            url = "url1",
            shareCountDisplay = "1",
            likeCountDisplay = "2"
        ),
        Clip(
            id = "id2",
            description = null,
            url = "url2",
            shareCountDisplay = "3",
            likeCountDisplay = "4"
        )
    )
)