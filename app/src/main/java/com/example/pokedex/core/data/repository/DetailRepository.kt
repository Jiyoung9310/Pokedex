package com.example.pokedex.core.data.repository

import com.example.pokedex.core.network.model.NetworkPokemonDetailResponse
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getPokemonDetailInfo(id: Int) : Flow<NetworkPokemonDetailResponse>
}