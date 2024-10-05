package com.example.storyteller.data.models

import com.google.gson.annotations.SerializedName

data class ClipDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("url")
    val url: String,

    @SerializedName("shareCountDisplay")
    val shareCountDisplay: String,

    @SerializedName("likeCountDisplay")
    val likeCountDisplay: String
)