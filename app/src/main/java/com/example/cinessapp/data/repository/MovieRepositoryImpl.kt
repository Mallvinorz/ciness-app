package com.example.cinessapp.data.repository

import com.example.cinessapp.data.mapper.toDomain
import com.example.cinessapp.data.remote.api.ApiService
import com.example.cinessapp.data.remote.api.NetworkResult
import com.example.cinessapp.data.remote.api.mapSuccess
import com.example.cinessapp.data.remote.api.safeApiCall
import com.example.cinessapp.domain.model.Genre
import com.example.cinessapp.domain.model.MovieList
import com.example.cinessapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieRepository {
    override suspend fun getMoviesGenres(): NetworkResult<List<Genre>> = safeApiCall {
        apiService.getMovieGenres()
    }.mapSuccess { it.toDomain() }

    override suspend fun getMoviesByGenres(genre: String): NetworkResult<MovieList> = safeApiCall {
        apiService.getMoviesByGenre(genre)
    }.mapSuccess { it.toDomain() }

    override suspend fun getNowPlayingMovies(): NetworkResult<MovieList> = safeApiCall {
        apiService.getMovieNowPlaying()
    }.mapSuccess { it.toDomain() }
}