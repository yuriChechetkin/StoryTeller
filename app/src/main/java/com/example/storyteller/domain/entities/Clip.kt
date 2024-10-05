package com.example.storyteller.domain.entities

data class Clip(
    val id: String,
    val description: String?,
    val url: String,
    val shareCountDisplay: String,
    val likeCountDisplay: String
)