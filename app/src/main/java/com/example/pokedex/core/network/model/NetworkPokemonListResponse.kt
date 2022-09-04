package com.example.pokedex.core.network.model

data class NetworkPokemonListResponse(
    val count : Int,
    val next : String,
    val previous : String?,
    val results : List<NetworkPokemonCommonName>
)

data class NetworkPokemonCommonName(
    val name : String,
    val url : String,
)