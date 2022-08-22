package com.example.pokedex.core.network.api

import com.example.pokedex.core.network.model.NetworkPokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface RetrofitPokemonNetworkApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20,
    ): NetworkPokemonListResponse

    @GET
    suspend fun getPokemonListMore(@Url nextUrl: String): NetworkPokemonListResponse
}