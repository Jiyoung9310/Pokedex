package com.example.pokedex.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.core.data.model.PokemonDetailInfo
import com.example.pokedex.core.data.model.PokemonTypes.Companion.toPokemonType
import com.example.pokedex.core.data.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    detailRepository: DetailRepository,
) : ViewModel() {
    val detailInfoState: StateFlow<PokemonInfoUiState> = detailRepository.getPokemonDetailInfo(savedStateHandle.get<Int>("id") ?: 0)
        .map { res ->
            PokemonInfoUiState.Success(
                info = PokemonDetailInfo(
                    id = res.id,
                    name = res.name,
                    height = "${(res.height / 10.0)}m",
                    weight = "${(res.weight / 10.0)}kg",
                    stats = res.stats.map { it.stat.name },
                    types = res.types.map { it.type.name.toPokemonType() },
                    abilities = res.abilities.map { it.ability.name }
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PokemonInfoUiState.Loading
        )

}