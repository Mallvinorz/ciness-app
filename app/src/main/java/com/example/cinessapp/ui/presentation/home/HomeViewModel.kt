package com.example.cinessapp.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinessapp.core.state.UiState
import com.example.cinessapp.data.remote.api.NetworkResult
import com.example.cinessapp.domain.model.Genre
import com.example.cinessapp.domain.model.Movie
import com.example.cinessapp.domain.model.MovieList
import com.example.cinessapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _genreState = MutableStateFlow<UiState<List<Genre>>>(UiState.Idle)
    val genreState: StateFlow<UiState<List<Genre>>> = _genreState.asStateFlow()

    private val _movieState = MutableStateFlow<UiState<MovieList>>(UiState.Idle)
    val movieState: StateFlow<UiState<MovieList>> = _movieState.asStateFlow()

    private val _selectedGenre = MutableStateFlow<Genre?>(null)
    val selectedGenre: StateFlow<Genre?> = _selectedGenre.asStateFlow()

    val filteredMovies: StateFlow<List<Movie>> = combine(
        _movieState, _selectedGenre
    ) { state, genre ->
        if (state is UiState.Success) {
            if (genre == null) state.data.results
            else state.data.results.filter { genre.id in it.genreIds }
        } else emptyList()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        getGenreListData()
    }

    private fun getGenreListData() {
        viewModelScope.launch {
            _genreState.value = UiState.Loading

            when (val result = repository.getMoviesGenres()) {
                is NetworkResult.Success -> {
                    _genreState.value = UiState.Success(result.data)

                    result.data.firstOrNull()?.let { firstGenre ->
                        _selectedGenre.value = firstGenre
                        getMoviesByGenre(firstGenre.id.toString())
                    }
                }
                is NetworkResult.HttpError -> _genreState.value =
                    UiState.Error("HTTP ERROR ${result.code}: ${result.message}")

                is NetworkResult.Exception -> _genreState.value =
                    UiState.Error(result.e.message ?: "Unknown error")
            }
        }
    }

    fun getMoviesByGenre(genreId: String) {
        viewModelScope.launch {
            val movieDeferred = async { repository.getMoviesByGenres(genreId) }

            when (val result = movieDeferred.await()) {
                is NetworkResult.Success -> _movieState.value = UiState.Success(result.data)
                is NetworkResult.HttpError -> _movieState.value =
                    UiState.Error("HTTP ERROR ${result.code}: ${result.message}")

                is NetworkResult.Exception -> _genreState.value =
                    UiState.Error(result.e.message ?: "Unknown error")
            }
        }
    }

    fun onGenreSelected(genre: Genre) {
        if (_selectedGenre.value?.id == genre.id) return
        _selectedGenre.value = genre
        getMoviesByGenre(genre.id.toString())
    }

//    fun getNowPlayingMovies() {
//        viewModelScope.launch {
//            _uiState.value = UiState.Loading
//            when (val result = repository.getNowPlayingMovies()) {
//                is NetworkResult.Success -> {
//                    _uiState.value = UiState.Success(result.data)
//                }
//
//                is NetworkResult.HttpError -> {
//                    _uiState.value = UiState.Error("HTTP ${result.code}: ${result.message}")
//                }
//
//                is NetworkResult.Exception -> {
//                    _uiState.value = UiState.Error(result.e.message ?: "Unknown error occurred")
//                }
//            }
//        }
//    }

}