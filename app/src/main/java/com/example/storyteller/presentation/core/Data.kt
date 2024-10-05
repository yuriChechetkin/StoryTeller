package com.example.storyteller.presentation.core

data class Data<T>(
    val content: T? = null,
    val loading: Boolean = false,
    val error: Exception? = null
)