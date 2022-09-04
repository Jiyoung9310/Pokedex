package com.example.pokedex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.pokedex.feature.detail.DetailScreen
import com.example.pokedex.feature.main.MainScreen

@Composable
fun PokeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = "main"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = "main") {
            MainScreen(
                navigateToDetail = { navController.navigate("detail/$it") },
            )
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            DetailScreen(backStackEntry.arguments?.getInt("id"))

        }
    }
}