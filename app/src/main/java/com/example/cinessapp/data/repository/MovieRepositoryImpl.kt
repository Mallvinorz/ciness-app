package com.example.cinessapp.data.repository

import com.example.cinessapp.data.mapper.toDomain
import com.example.cinessapp.data.remote.api.ApiService
import com.example.cinessapp.data.remote.api.NetworkResult
import com.example.cinessapp.data.remote.api.mapSuccess
import com.example.cinessapp.data.remote.api.safeApiCall
import com.example.cinessapp.domain.model.MovieList
import com.example.cinessapp.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val apiService: ApiService,
) : MovieRepository {
    override suspend fun getNowPlayingMovies(): NetworkResult<MovieList> = safeApiCall {
        apiService.getMovieNowPlaying()
    }.mapSuccess { it.toDomain() }
}