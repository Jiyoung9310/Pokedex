package com.example.pokedex.core.data.repository

import com.example.pokedex.core.network.PokemonNetworkDataSource
import com.example.pokedex.core.network.model.NetworkPokemonDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val pokemonNetworkDataSource: PokemonNetworkDataSource
): DetailRepository {
    override fun getPokemonDetailInfo(id: Int): Flow<NetworkPokemonDetailResponse> = flow {
        emit(pokemonNetworkDataSource.getPokemonDetailInfo(id))
    }
}