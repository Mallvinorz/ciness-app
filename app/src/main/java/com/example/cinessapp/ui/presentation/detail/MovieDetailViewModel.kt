package com.example.cinessapp.ui.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinessapp.core.state.UiState
import com.example.cinessapp.data.remote.api.NetworkResult
import com.example.cinessapp.domain.model.Cast
import com.example.cinessapp.domain.model.MovieDetail
import com.example.cinessapp.domain.model.Review
import com.example.cinessapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _movieDetailState = MutableStateFlow<UiState<MovieDetail>>(UiState.Idle)
    val movieDetailState: StateFlow<UiState<MovieDetail>> = _movieDetailState.asStateFlow()

    private val _castsListState = MutableStateFlow<UiState<List<Cast>>>(UiState.Idle)
    val castsListState: StateFlow<UiState<List<Cast>>> = _castsListState.asStateFlow()

    private val _reviewListState = MutableStateFlow<UiState<List<Review>>>(UiState.Idle)
    val reviewListState: StateFlow<UiState<List<Review>>> = _reviewListState.asStateFlow()

    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    init {
        getMovieDetailById(movieId)
        getMovieCastsById(movieId)
        getMovieReviewsById(movieId)
    }

    fun getMovieDetailById(movieId: Int) {
        viewModelScope.launch {
            _movieDetailState.value = UiState.Loading

            when (val result = repository.getDetailMovieById(movieId)) {
                is NetworkResult.Success -> {
                    _movieDetailState.value = UiState.Success(result.data)
                }

                is NetworkResult.HttpError -> _movieDetailState.value =
                    UiState.Error("HTTP ERROR ${result.code}: ${result.message}")


                is NetworkResult.Exception -> _movieDetailState.value =
                    UiState.Error(result.e.message ?: "Unknown error")
            }
        }
    }

    fun getMovieCastsById(movieId: Int) {
        viewModelScope.launch {
            _castsListState.value = UiState.Loading

            when (val result = repository.getMovieCreditsById(movieId)) {
                is NetworkResult.Success -> {
                    _castsListState.value = UiState.Success(result.data.take(3))
                }

                is NetworkResult.HttpError -> _castsListState.value =
                    UiState.Error("HTTP ERROR ${result.code}: ${result.message}")

                is NetworkResult.Exception -> _castsListState.value =
                    UiState.Error(result.e.message ?: "Unknown error")
            }
        }
    }

    fun getMovieReviewsById(movieId: Int) {
        viewModelScope.launch {
            _reviewListState.value = UiState.Loading

            when (val result = repository.getMovieReviewsById(movieId)) {
                is NetworkResult.Success -> {
                    _reviewListState.value = UiState.Success(result.data)
                }

                is NetworkResult.HttpError -> _reviewListState.value =
                    UiState.Error("HTTP ERROR ${result.code}: ${result.message}")

                is NetworkResult.Exception -> _reviewListState.value =
                    UiState.Error(result.e.message ?: "Unknown error")
            }
        }
    }
}