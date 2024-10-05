package com.example.storyteller.domain.providers

import com.example.storyteller.domain.entities.Clip

interface ShareProvider {

    fun shareClip(clip: Clip)
}