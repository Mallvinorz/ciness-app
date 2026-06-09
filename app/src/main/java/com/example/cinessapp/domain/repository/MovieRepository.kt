package com.example.cinessapp.domain.repository

import com.example.cinessapp.data.remote.api.NetworkResult
import com.example.cinessapp.domain.model.MovieList

interface MovieRepository {
    suspend fun getNowPlayingMovies(): NetworkResult<MovieList>
}