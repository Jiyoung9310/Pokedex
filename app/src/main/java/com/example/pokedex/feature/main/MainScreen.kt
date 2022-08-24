package com.example.pokedex.feature.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.theme.Pink80
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun MainScreen(viewModel: MainViewModel) {

    val feedState by viewModel.feedState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (feedState) {
            PokemonNameUiState.Loading -> {
                FeedLoading()
            }
            is PokemonNameUiState.Success -> {
                PokemonFeed(
                    (feedState as PokemonNameUiState.Success),
                    viewModel::pageLoadMore
                )
            }
        }

    }

}

@Composable
fun FeedLoading(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator()
}

@Composable
fun PokemonFeed(
    feedState: PokemonNameUiState.Success,
    loadMore : () -> Unit,
) {

    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(feedState.feed, key = { it.name }) { item ->
            Card (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp, start = 15.dp, end = 15.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Pink80
            )
            {
                Box(modifier = Modifier.padding(20.dp)) {
                    Text(item.name)
                }

            }
        }
    }

    listState.OnBottomReached {
        // do on load more
        loadMore()
    }
}

@Composable
fun LazyListState.OnBottomReached(
    buffer : Int = 2,
    loadMore : () -> Unit,
){
    // state object which tells us if we should load more
    val shouldLoadMore = remember {
        derivedStateOf {
            // get last visible item
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?:
                // list is empty
                // return false here if loadMore should not be invoked if the list is empty
                return@derivedStateOf true

            // Check if last visible item is the last item in the list
            lastVisibleItem.index == layoutInfo.totalItemsCount - buffer
        }

    }

    // Convert the state into a cold flow and collect
    LaunchedEffect(shouldLoadMore){
        snapshotFlow { shouldLoadMore.value }
            .distinctUntilChanged()
            .collect {
                // if should load more, then invoke loadMore
                if (it) loadMore()
            }
    }
}