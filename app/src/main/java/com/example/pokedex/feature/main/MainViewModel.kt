package com.example.pokedex.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.core.data.model.PokemonName
import com.example.pokedex.core.data.repository.MainRepository
import com.example.pokedex.core.network.model.NetworkPokemonListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository,
): ViewModel() {

    private var nextUrl : String? = null
    private val feedFullList = mutableListOf<PokemonName>()

    private val _feedState: MutableStateFlow<PokemonNameUiState> = MutableStateFlow(PokemonNameUiState.Loading)
    val feedState : StateFlow<PokemonNameUiState> = _feedState

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            mainRepository.getPokemonNames()
                .mapToFeedState()
                .collect {
                    _feedState.value = it
                }
        }
    }

    fun pageLoadMore() {
        nextUrl?.let {
            viewModelScope.launch {
                mainRepository.getPokemonNamesMore(it)
                    .mapToFeedState()
                    .collect {
                        _feedState.value = it
                    }

            }
        }
    }

    private fun Flow<NetworkPokemonListResponse>.mapToFeedState(): Flow<PokemonNameUiState> =
        map { pokemonData ->
            nextUrl = pokemonData.next

            PokemonNameUiState.Success(
                feedFullList + pokemonData.results.map {
                    PokemonName(it.name)
                }
            )
        }.onEach {
            feedFullList.clear()
            feedFullList.addAll(it.feed)
        }
}