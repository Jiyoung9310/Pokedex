package com.example.pokedex.core.data.repository

import com.example.pokedex.core.network.PokemonNetworkDataSource
import com.example.pokedex.core.network.model.NetworkPokemonListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val pokemonNetworkDataSource: PokemonNetworkDataSource
) : MainRepository {
    override fun getPokemonNames(offset: Int): Flow<NetworkPokemonListResponse> = flow {
        emit(pokemonNetworkDataSource.getPokemonList(offset, 20))
    }
}