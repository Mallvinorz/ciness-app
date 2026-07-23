package com.example.cinessapp.domain.repository

import com.example.cinessapp.data.remote.api.NetworkResult
import com.example.cinessapp.domain.model.Cast
import com.example.cinessapp.domain.model.Genre
import com.example.cinessapp.domain.model.MovieDetail
import com.example.cinessapp.domain.model.MovieList
import com.example.cinessapp.domain.model.Review
import com.example.cinessapp.domain.model.VideoTrailer

interface MovieRepository {

    suspend fun getMoviesGenres(): NetworkResult<List<Genre>>

    suspend fun getMoviesByGenres(genre: String): NetworkResult<MovieList>
    suspend fun getNowPlayingMovies(): NetworkResult<MovieList>

    suspend fun getDetailMovieById(movieId: Int): NetworkResult<MovieDetail>

    suspend fun getMovieCreditsById(movieId: Int): NetworkResult<List<Cast>>

    suspend fun getMovieReviewsById(movieId: Int): NetworkResult<List<Review>>

    suspend fun getDetailMovideVideosById(movieId: Int): NetworkResult<VideoTrailer>
}