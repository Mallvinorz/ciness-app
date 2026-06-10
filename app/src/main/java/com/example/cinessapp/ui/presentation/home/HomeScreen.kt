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

    Column(modifier = Modifier.fillMaxSize()) {
        TopMenu()

        when (val state = genreState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            }

            is UiState.Error -> {
                Text(
                    text = "Failed to load genres", color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            is UiState.Success -> {
                GenreFilterRow(
                    genres = state.data,
                    selectedGenre = selectedGenre,
                    onGenreSelected = viewModel::onGenreSelected,
                )
            }

            is UiState.Idle -> Unit
        }
        Spacer(modifier = Modifier.size(8.dp))

        when (val state = movieState) {
            is UiState.Idle -> Unit
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator(modifier = Modifier.size(24.dp)) }
            }

            is UiState.Success -> {
                MovieGrid(movieGrid = filteredMovies)
            }

            is UiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
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

@Preview
@Composable
private fun HomeScreenPreview() {
    CinessAppTheme() {
        HomeScreen()
    }
}