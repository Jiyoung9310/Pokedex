package com.example.pokedex.core.network.retrofit

import com.example.pokedex.core.network.PokemonNetworkDataSource
import com.example.pokedex.core.network.api.RetrofitPokemonNetworkApi
import com.example.pokedex.core.network.model.NetworkPokemonDetailResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetwork @Inject constructor(
    private val retrofitPokemonApi : RetrofitPokemonNetworkApi
) : PokemonNetworkDataSource {

    override suspend fun getPokemonList(
        offset: Int, limit: Int
    ) = retrofitPokemonApi.getPokemonList(offset, limit)

    override suspend fun getPokemonDetailInfo(id: Int): NetworkPokemonDetailResponse = retrofitPokemonApi.getPokemonInfo(id)
}
