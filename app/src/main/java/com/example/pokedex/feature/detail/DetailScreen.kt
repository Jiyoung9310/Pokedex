package com.example.pokedex.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CoilImage(
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp),
                imageModel = info.imageUrl,
                bitmapPalette = BitmapPalette {
                    palette = it
                },
            )

            Text(
                text = "No${info.id}. ${info.name}",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            LazyRow {
                items(info.types) { item ->
                    Card(
                        modifier = Modifier.padding(top = 20.dp, start = 10.dp, end = 10.dp),
                        backgroundColor = item.color,
                        elevation = 3.dp,
                        shape = RoundedCornerShape(16.dp)
                    ) {

                        Text(
                            modifier = Modifier
                                .padding(
                                    top = 4.dp, bottom = 5.dp,
                                    start = 20.dp, end = 20.dp
                                ),
                            text = item.name,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "height",
                        style = TextStyle(
                            color = Color(palette?.mutedSwatch?.titleTextColor ?: 0),
                            fontSize = 20.sp,
                        )
                    )
                    Text(
                        text = info.height,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Column(
                    modifier = Modifier.padding(bottom = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "weight",
                        style = TextStyle(
                            color = Color(palette?.mutedSwatch?.titleTextColor ?: 0),
                            fontSize = 20.sp,
                        )
                    )
                    Text(
                        text = info.weight,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

            }

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