package com.example.cinessapp.ui.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cinessapp.core.state.UiState
import com.example.cinessapp.domain.model.Genre
import com.example.cinessapp.domain.model.Movie
import com.example.cinessapp.domain.model.MovieList
import com.example.cinessapp.ui.presentation.home.components.GenreFilterRow
import com.example.cinessapp.ui.presentation.home.components.MovieGrid
import com.example.cinessapp.ui.presentation.home.components.TopMenu
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val genreState by viewModel.genreState.collectAsStateWithLifecycle()
    val movieState by viewModel.movieState.collectAsStateWithLifecycle()
    val selectedGenre by viewModel.selectedGenre.collectAsStateWithLifecycle()
    val filteredMovies by viewModel.filteredMovies.collectAsStateWithLifecycle()

    HomeScreenContent(
        genreState = genreState,
        movieState = movieState,
        selectedGenre = selectedGenre,
        filteredMovies = filteredMovies,
        onGenreSelected = viewModel::onGenreSelected,
    )
}

@Composable
private fun HomeScreenContent(
    genreState: UiState<List<Genre>>,
    movieState: UiState<MovieList>,
    selectedGenre: Genre?,
    filteredMovies: List<Movie>,
    onGenreSelected: (Genre) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        TopMenu()
        Spacer(modifier = modifier.size(8.dp))

        when (genreState) {
            is UiState.Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = modifier.size(24.dp))
                }
            }

            is UiState.Error -> {
                Text(
                    text = "Failed to load genres", color = MaterialTheme.colorScheme.error,
                    modifier = modifier.padding(16.dp)
                )
            }

            is UiState.Success -> {
                GenreFilterRow(
                    genres = genreState.data,
                    selectedGenre = selectedGenre,
                    onGenreSelected = onGenreSelected,
                )
            }

            is UiState.Idle -> Unit
        }
        Spacer(modifier = modifier.size(8.dp))

        when (movieState) {
            is UiState.Idle -> Unit
            is UiState.Loading -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator(modifier = Modifier.size(24.dp)) }
            }

            is UiState.Success -> {
                MovieGrid(movieGrid = filteredMovies)
            }

            is UiState.Error -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = movieState.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
//        Text(
//            "Now Playing",
//            modifier = Modifier.padding(horizontal = 16.dp),
//            style = MaterialTheme.typography.headlineLarge,
//            color = MaterialTheme.colorScheme.primary
//        )
//        Spacer(modifier = Modifier.size(20.dp))
    }
}


@Preview(showBackground = true)
@Composable
private fun HomeScreenSuccessPreview() {
    val fakeGenres = listOf(
        Genre(28, "Action"),
        Genre(12, "Adventure"),
        Genre(35, "Comedy"),
    )

    val fakeMovies = listOf(
        Movie(
            adult = false,
            backdropPath = "",
            genreIds = listOf(0,1),
            id = 1,
            oriLanguage = "",
            oriTitle = "Test Title",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            releaseDate = "2020",
            title = "Title",
            video = false,
            voteAverage = 4.6,
            voteCount = 34
        ),
        Movie(
            adult = false,
            backdropPath = "",
            genreIds = listOf(0,1),
            id = 2,
            oriLanguage = "",
            oriTitle = "Test Title",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            releaseDate = "2020",
            title = "Title",
            video = false,
            voteAverage = 4.6,
            voteCount = 34
        ),
    )

    CinessAppTheme() {
        HomeScreenContent(
            genreState = UiState.Success(fakeGenres),
            movieState = UiState.Success(MovieList(1, fakeMovies,1, 2)),
            selectedGenre = fakeGenres.first(),
            filteredMovies = fakeMovies,
            onGenreSelected = {}
        )
    }
}