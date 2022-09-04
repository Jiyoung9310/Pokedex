package com.example.pokedex.feature.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pokedex.R
import com.example.pokedex.feature.main.FeedLoading

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {
    val detailInfoState by viewModel.detailInfoState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when(detailInfoState) {
            is PokemonInfoUiState.Loading -> {
                FeedLoading()
            }
            is PokemonInfoUiState.Success -> {
                DetailInfo(detailInfoState as PokemonInfoUiState.Success)
            }
        }
    }

}

@Composable
fun DetailInfo(
    infoUiState: PokemonInfoUiState.Success
) {
    val info = infoUiState.info

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier
                .height(150.dp)
                .width(150.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data(info.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.pokemon_monster_ball),
        )
        Text(text = "id : ${info.id}")
        Text(text = "name : ${info.name}")
        Text(text = "weight : ${info.weight}")
        Text(text = "height : ${info.height}")
        Text(text = "types : ${info.types}")
        Text(text = "stats: ${info.stats}")
        Text(text = "abilities : ${info.abilities}")
    }
}