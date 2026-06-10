package com.example.cinessapp.ui.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinessapp.domain.model.Movie
import com.example.cinessapp.domain.model.MovieItem
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun MovieGrid(movieGrid: List<Movie>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movieGrid, key = { it.id}) { movie ->
            MovieGridItem(movie)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MovieGridPreview() {
    CinessAppTheme() {
//        MovieGrid(
//            movieGrid = listOf(
//                MovieItem(
//                    id = 1,
//                    posterPath = "",
//                    title = "JurasicPark",
//                    releaseYear = "2020",
//                    rating = 4.9
//                ),
//                MovieItem(
//                    id = 1,
//                    posterPath = "",
//                    title = "JurasicPark",
//                    releaseYear = "2020",
//                    rating = 4.9
//                ),
//                MovieItem(
//                    id = 1,
//                    posterPath = "",
//                    title = "JurasicPark",
//                    releaseYear = "2020",
//                    rating = 4.9
//                ),
//                MovieItem(
//                    id = 1,
//                    posterPath = "",
//                    title = "JurasicPark",
//                    releaseYear = "2020",
//                    rating = 4.9
//                )
//            )
//        )
    }
}