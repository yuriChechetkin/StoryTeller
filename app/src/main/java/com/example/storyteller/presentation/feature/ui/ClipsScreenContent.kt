package com.example.storyteller.presentation.feature.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.IosShare
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.storyteller.domain.entities.Clip
import com.example.storyteller.presentation.feature.ViewState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
internal fun ClipsScreenContent(
    viewState: ViewState.Content,
    onLoadMore: (String) -> Unit,
    likeClip: (String) -> Unit,
    shareClip: (Clip) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AppBar(title = viewState.title)
        },
        modifier = modifier,
    ) {
        ClipsPager(
            clipItems = viewState.clips,
            onLoadMore = onLoadMore,
            likeClip = likeClip,
            shareClip = shareClip,
            modifier = modifier.fillMaxSize(),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ClipsPager(
    clipItems: List<Clip>,
    onLoadMore: (String) -> Unit,
    likeClip: (String) -> Unit,
    shareClip: (Clip) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState {
        clipItems.size
    }

    val reachedBottom: Boolean by remember {
        derivedStateOf { pagerState.reachedBottom() }
    }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom) {
            onLoadMore(clipItems.last().id)
        }
    }

    VerticalPager(pagerState) {
        val clip = clipItems[it]
        ClipPage(
            clip = clip,
            likeClip = likeClip,
            shareClip = shareClip,
            modifier = modifier
        )
    }
}

@Composable
private fun ClipPage(
    clip: Clip,
    likeClip: (String) -> Unit,
    shareClip: (Clip) -> Unit,
    modifier: Modifier = Modifier
) {
    Box {
        ClipPlayer(url = clip.url)
        ClipDescription(description = clip.description ?: "No description")
        ClipActionsPanel(clip = clip, likeClip = likeClip, shareClip = shareClip)
    }
}

@Composable
private fun BoxScope.ClipDescription(
    description: String
) {
    Row(
        Modifier
            .align(Alignment.BottomStart)
            .padding(24.dp)
    ) {
        Text(
            text = description,
            color = Color.White
        )
    }
}

@Composable
private fun BoxScope.ClipActionsPanel(
    clip: Clip,
    likeClip: (String) -> Unit,
    shareClip: (Clip) -> Unit,
) {
    Column(
        Modifier
            .align(Alignment.BottomEnd)
            .padding(24.dp)
    ) {
        ClipAction(
            icon = Icons.Outlined.FavoriteBorder,
            counter = clip.likeCountDisplay,
            onClick = { likeClip(clip.id) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ClipAction(
            icon = Icons.Outlined.IosShare,
            counter = clip.shareCountDisplay,
            onClick = { shareClip(clip) }
        )
    }
}

@Composable
private fun ClipAction(
    icon: ImageVector,
    counter: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Icon(
            imageVector = icon,
            tint = Color.White,
            modifier = modifier
                .clip(CircleShape)
                .clickable { onClick() }
                .background(color = Color.Black.copy(alpha = 0.4f))
                .padding(12.dp)
                .size(32.dp),
            contentDescription = null
        )
        Text(
            text = counter,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
private fun PagerState.reachedBottom(offset: Int = 2): Boolean {
    if (pageCount == 0) return false
    return this.pageCount - offset == this.currentPage
}