package com.example.pokedex.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.R
import com.example.pokedex.ui.navigation.PokeNavHost


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonApp(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = Modifier,
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
            )
        },
        content = { padding ->
            PokeNavHost(
                modifier = Modifier
                    .padding(padding),
                navController = navController
            )

        }
    )

}
