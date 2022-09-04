package com.example.pokedex.feature.detail

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(
    id : Int?
) {
    Text(text = "$id")

}