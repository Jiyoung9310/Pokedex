package com.example.pokedex.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.core.data.model.PokemonDetailInfo
import com.example.pokedex.core.data.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
                    height = res.height,
                    weight = res.weight,
                    stats = res.stats.map { it.stat.name },
                    types = res.types.map { it.type.name },
                    abilities = res.abilities.map { it.ability.name }
                )
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PokemonInfoUiState.Loading
        )

}