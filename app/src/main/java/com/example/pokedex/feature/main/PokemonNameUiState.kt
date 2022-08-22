package com.example.pokedex.feature.main

import com.example.pokedex.core.data.model.PokemonName

sealed interface PokemonNameUiState {
    object Loading : PokemonNameUiState
    data class Success(
        /**
         * The list of news resources contained in this feed.
         */
        val feed: List<PokemonName>
    ) : PokemonNameUiState
}