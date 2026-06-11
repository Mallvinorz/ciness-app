package com.example.cinessapp.ui.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinessapp.core.state.UiState
import com.example.cinessapp.data.remote.api.NetworkResult
import com.example.cinessapp.domain.model.MovieDetail
import com.example.cinessapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movieDetailState = MutableStateFlow<UiState<MovieDetail>>(UiState.Idle)
    val movieDetailState: StateFlow<UiState<MovieDetail>> = _movieDetailState.asStateFlow()

    private val _castsListState = MutableStateFlow<UiState<>>

    fun getMovieDetailById(movieId: Int) {
        viewModelScope.launch {
            _movieDetailState.value = UiState.Loading

            when (val result = repository.getDetailMovie(movieId)) {
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
}