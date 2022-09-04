package com.example.pokedex.feature.detail

import com.example.pokedex.core.data.model.PokemonDetailInfo

sealed interface PokemonInfoUiState {
    object Loading : PokemonInfoUiState
    data class Success(
        val info: PokemonDetailInfo
    ) : PokemonInfoUiState
}