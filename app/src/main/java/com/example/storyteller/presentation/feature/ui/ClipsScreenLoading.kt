package com.example.storyteller.presentation.feature.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.storyteller.presentation.feature.ViewState

@Composable
internal fun ClipsScreenLoading(
    viewState: ViewState.Loading,
    modifier: Modifier = Modifier
) {
    //TODO("Implement shimmer screen that fits content screen: jira task reference")
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = modifier,
            color = Color.LightGray,
        )
    }
}