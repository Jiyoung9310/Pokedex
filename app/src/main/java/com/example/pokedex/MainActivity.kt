package com.example.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pokedex.core.data.model.PokemonName
import com.example.pokedex.feature.main.MainScreen
import com.example.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainScreen(fakelist)
                }
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
        MainScreen(fakelist)
    }
}