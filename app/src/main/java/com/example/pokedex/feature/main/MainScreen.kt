package com.example.pokedex.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val feedState by viewModel.feedState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (feedState) {
            PokemonNameUiState.Loading -> {
                FeedLoading()
            }
            is PokemonNameUiState.Success -> {
                PokemonFeed(feedState as PokemonNameUiState.Success)
            }
        }

    }

}

@Composable
fun FeedLoading(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator()
}

@Composable
fun PokemonFeed(
    feedState: PokemonNameUiState.Success,
) {
    LazyColumn {
        items(feedState.feed) { item ->
            Text(item.name)
        }
    }
}