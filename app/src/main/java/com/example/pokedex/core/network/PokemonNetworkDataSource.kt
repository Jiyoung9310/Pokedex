package com.example.pokedex.core.network

import com.example.pokedex.core.network.model.NetworkPokemonListResponse

interface PokemonNetworkDataSource {
    suspend fun getPokemonList(offset: Int, limit: Int): NetworkPokemonListResponse
}