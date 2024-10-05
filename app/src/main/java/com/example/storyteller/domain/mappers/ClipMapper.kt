package com.example.storyteller.domain.mappers

import com.example.storyteller.data.models.ClipDto
import com.example.storyteller.data.models.ClipItemsDto
import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.domain.entities.ClipItems

internal fun ClipItemsDto.toDomain(): ClipItems {
    return ClipItems(
        feedTitle = this.feedTitle,
        clips = this.clips.map(ClipDto::toDomain)
    )
}

private fun ClipDto.toDomain(): Clip {
    return Clip(
        id = this.id,
        description = this.description,
        url = this.url,
        shareCountDisplay = this.shareCountDisplay,
        likeCountDisplay = this.likeCountDisplay
    )
}