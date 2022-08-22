package com.example.pokedex.core.data.repository

import com.example.pokedex.core.network.PokemonNetworkDataSource
import com.example.pokedex.core.network.model.NetworkPokemonListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val pokemonNetworkDataSource: PokemonNetworkDataSource
) : MainRepository {
    override fun getPokemonNames(): Flow<NetworkPokemonListResponse> = flow {
        emit(pokemonNetworkDataSource.getPokemonList(0, 20))
    }

    override fun getPokemonNamesMore(url: String): Flow<NetworkPokemonListResponse> = flow {
        emit(pokemonNetworkDataSource.getPokemonListMore(url))
    }
}