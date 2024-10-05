package com.example.storyteller.data

import com.example.storyteller.data.models.ClipItemsDto
import retrofit2.http.GET

interface ClipsService {

    @GET("clips/clipssample/clips")
    suspend fun getClips(): ClipItemsDto
}