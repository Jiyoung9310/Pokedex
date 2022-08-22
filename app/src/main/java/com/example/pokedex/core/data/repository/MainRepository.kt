package com.example.pokedex.core.data.repository


import com.example.pokedex.core.network.model.NetworkPokemonListResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getPokemonNames() : Flow<NetworkPokemonListResponse>
    fun getPokemonNamesMore(url: String): Flow<NetworkPokemonListResponse>
}