package com.example.pokedex.core.network.model

data class NetworkPokemonDetailResponse (
    val id : Int,
    val name: String,
    val height: Int?, // m단위
    val weight: Int?, // g단위
    val stats: List<PokemonStats>?,
    val types: List<PokemonTypes>?,
    val abilities: List<PokemonAbilities>?,
)

data class PokemonStats(
    val base_stat: Int,
    val effort: Int,
    val stat: NetworkPokemonCommonName
)

data class PokemonTypes(
    val slot: Int,
    val type: NetworkPokemonCommonName
)

data class PokemonAbilities(
    val slot: Int,
    val is_hidden: Boolean,
    val ability: NetworkPokemonCommonName
)