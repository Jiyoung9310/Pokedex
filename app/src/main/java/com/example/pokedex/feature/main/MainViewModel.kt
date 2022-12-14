package com.example.pokedex.feature.main

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.core.data.model.PokemonInfo
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
    private var offset: Int = 0
    private val feedFullList = mutableListOf<PokemonInfo>()

    private val _feedState: MutableStateFlow<PokemonNameUiState> = MutableStateFlow(PokemonNameUiState.Loading)
    val feedState : StateFlow<PokemonNameUiState> = _feedState

    init {
        getNextPagePokemonList()
    }

    private fun getNextPagePokemonList() {
        viewModelScope.launch {
            mainRepository.getPokemonNames(offset)
                .mapToFeedState()
                .collect {
                    _feedState.value = it
                }
        }
    }

    fun pageLoadMore() {
        nextUrl?.let {
            getNextPagePokemonList()
        }
    }

    fun onClickItem() {

    }

    private fun Flow<NetworkPokemonListResponse>.mapToFeedState(): Flow<PokemonNameUiState> =
        map { pokemonData ->
            offset += pokemonData.results.count()
            nextUrl = pokemonData.next

            PokemonNameUiState.Success(
                feedFullList + pokemonData.results.map {
                    PokemonInfo(it.url.toPokemonId(), it.name)
                }
            )
        }.onEach {
            feedFullList.clear()
            feedFullList.addAll(it.feed)
        }

    private fun String.toPokemonId() : Int {
        return try {
            val lastPath = this.toUri().lastPathSegment
            lastPath?.toIntOrNull() ?: 0
        } catch(e: Exception) {
            0
        }
    }
}