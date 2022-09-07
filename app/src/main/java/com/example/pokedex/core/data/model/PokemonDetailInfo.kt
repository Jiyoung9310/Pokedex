package com.example.pokedex.core.data.model

import androidx.compose.ui.graphics.Color

data class PokemonDetailInfo (
    val id: Int,
    val name: String,
    val height: String,
    val weight: String,
    val types: List<PokemonTypes>,
    val stats: List<PokemonStat>,
    val abilities: List<PokemonAbility>,
)  {
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}

data class PokemonAbility(
    val name: String,
    val isHidden: Boolean,
)

data class PokemonStat(
    val name: String,
    val baseStat: Int
)

enum class PokemonTypes(val color: Color) {
    normal(Color(127, 135, 142)),
    fighting(Color(172, 53, 88)),
    flying(Color(131, 150, 208)),
    poison(Color(142, 86, 181)),
    ground(Color(189, 104, 63)),
    rock(Color(181, 170, 125)),
    bug(Color(139, 182, 56)),
    ghost(Color(70, 83, 151)),
    steel(Color(86, 122, 140)),
    fire(Color(233, 143, 79)),
    water(Color(78, 123, 196)),
    grass(Color(105, 171, 83)),
    electric(Color(232, 202, 72)),
    psychic(Color(221, 97, 102)),
    ice(Color(125, 194, 178)),
    dragon(Color(40, 86, 176)),
    dark(Color(69, 64, 81)),
    fairy(Color(211, 123, 218)),
    unknown(Color.LightGray),
    shadow(Color.DarkGray);

    companion object {
        fun String.toPokemonType() : PokemonTypes = when(this) {
            normal.name -> normal
            fighting.name -> fighting
            flying.name -> flying
            poison.name -> poison
            ground.name -> ground
            rock.name -> rock
            bug.name -> bug
            ghost.name -> ghost
            steel.name -> steel
            fire.name -> fire
            water.name -> water
            grass.name -> grass
            electric.name -> electric
            psychic.name -> psychic
            ice.name -> ice
            dragon.name -> dragon
            dark.name -> dark
            fairy.name -> fairy
            shadow.name -> shadow
            else -> unknown
        }
    }
}