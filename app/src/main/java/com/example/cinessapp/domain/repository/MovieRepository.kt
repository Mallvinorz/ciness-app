package com.example.cinessapp.domain.repository

import com.example.cinessapp.data.remote.api.NetworkResult
import com.example.cinessapp.domain.model.Genre
import com.example.cinessapp.domain.model.MovieList

interface MovieRepository {

    suspend fun getMoviesGenres(): NetworkResult<List<Genre>>

    suspend fun getMoviesByGenres(genre: String): NetworkResult<MovieList>
    suspend fun getNowPlayingMovies(): NetworkResult<MovieList>
}