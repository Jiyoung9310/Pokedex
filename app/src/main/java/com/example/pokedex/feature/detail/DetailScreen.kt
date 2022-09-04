package com.example.pokedex.feature.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.example.pokedex.feature.main.FeedLoading
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.palette.BitmapPalette

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
    infoUiState: PokemonInfoUiState.Success,
) {
    val info = infoUiState.info
    var palette by remember { mutableStateOf<Palette?>(null) }
    Surface(
        color = Color(palette?.mutedSwatch?.rgb ?: 1)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CoilImage(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp),
                imageModel = info.imageUrl,
                bitmapPalette = BitmapPalette {
                    palette = it
                },
            )
            Text(text = "id : ${info.id}", style = TextStyle(color = Color(palette?.mutedSwatch?.titleTextColor ?: 0)))
            Text(text = "name : ${info.name}")
            Text(text = "weight : ${info.weight}")
            Text(text = "height : ${info.height}")
            Text(text = "types : ${info.types}")
            Text(text = "stats: ${info.stats}")
            Text(text = "abilities : ${info.abilities}")
        }
    }
}