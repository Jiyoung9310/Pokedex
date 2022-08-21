package com.example.pokedex.feature.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.pokedex.core.data.model.PokemonName

@Composable
fun MainScreen(pokemonList: List<PokemonName>) {

    LazyColumn {
        items(
            items = pokemonList,
            key = { it.name },
            itemContent = { item ->
                Text(item.name)
            }
        )
    }

}