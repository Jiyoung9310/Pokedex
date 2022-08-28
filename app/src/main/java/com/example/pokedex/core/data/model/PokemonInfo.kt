package com.example.pokedex.core.data.model

data class PokemonInfo (
    val id: Int,
    val name: String,
) {
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}