package com.example.pokedex.feature.main

import com.example.pokedex.core.data.model.PokemonInfo

sealed interface PokemonNameUiState {
    object Loading : PokemonNameUiState
    data class Success(
        val feed: List<PokemonInfo>
    ) : PokemonNameUiState
}