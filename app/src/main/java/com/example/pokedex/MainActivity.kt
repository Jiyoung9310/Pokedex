package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokedex.core.data.model.PokemonName
import com.example.pokedex.feature.main.MainScreen
import com.example.pokedex.feature.main.MainViewModel
import com.example.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                MainScreen(mainViewModel)
            }
        }
    }
}

private val fakelist = listOf(
    PokemonName("이상해씨"),
    PokemonName("파이리"),
    PokemonName("꼬부기"),
    PokemonName("피카츄"),
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {

    }
}