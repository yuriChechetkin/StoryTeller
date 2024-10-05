package com.example.storyteller.presentation.feature.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.storyteller.presentation.feature.ViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ClipsScreenError(
    viewState: ViewState.Error,
    retryLoading: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = viewState.title) })
        },
        modifier = modifier,
    ) { paddingValue ->
        Box(
            modifier = modifier
                .padding(paddingValue)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(32.dp)
                    .height(200.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = viewState.errorDescription,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(32.dp)
                    )

                    Button(
                        onClick = retryLoading,
                        modifier = modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = viewState.retryButtonText)
                    }
                }
            }
        }
    }
}