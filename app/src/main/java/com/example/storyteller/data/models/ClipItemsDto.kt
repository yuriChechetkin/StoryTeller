package com.example.storyteller.data.models

import com.google.gson.annotations.SerializedName


data class ClipItemsDto(
    @SerializedName("feedTitle")
    val feedTitle: String,

    @SerializedName("clips")
    val clips: List<ClipDto>
)