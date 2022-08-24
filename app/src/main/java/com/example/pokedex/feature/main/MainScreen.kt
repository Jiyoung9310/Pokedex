package com.example.pokedex.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.theme.Pink80

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
            Card (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp, start = 15.dp, end = 15.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Pink80
            )
            {
                Box(modifier = Modifier.padding(20.dp)) {
                    Text(item.name)
                }

            }
        }
    }
}