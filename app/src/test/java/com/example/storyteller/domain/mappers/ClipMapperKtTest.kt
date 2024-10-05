package com.example.storyteller.domain.mappers

import com.example.storyteller.data.models.ClipDto
import com.example.storyteller.data.models.ClipItemsDto
import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.domain.entities.ClipItems
import org.junit.Test
import kotlin.test.assertEquals

internal class ClipMapperKtTest {

    @Test
    fun `GIVEN clip items dto WHEN map to domain state THEN correct state returned`() {

        val clipsDto = ClipItemsDto(
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

        val clipsDomain = ClipItems(
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

        assertEquals(clipsDomain, clipsDto.toDomain())
    }
}