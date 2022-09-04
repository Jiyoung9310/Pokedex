package com.example.pokedex.core.data.model

data class PokemonDetailInfo (
    val id: Int,
    val name: String,
    val height: String,
    val weight: String,
    val types: List<String>,
    val stats: List<String>,
    val abilities: List<String>,
)  {
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}