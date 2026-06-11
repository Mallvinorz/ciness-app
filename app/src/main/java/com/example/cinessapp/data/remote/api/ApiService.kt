package com.example.cinessapp.data.remote.api

import com.example.cinessapp.data.remote.dto.GenreListDto
import com.example.cinessapp.data.remote.dto.MovieDetailDto
import com.example.cinessapp.data.remote.dto.MovieListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("genre/movie/list")
    suspend fun getMovieGenres(): Response<GenreListDto>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(@Query("with_genres") genres: String): Response<MovieListDto>

    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(): Response<MovieListDto>

    @GET("movie/{movieId}")
    suspend fun getMovieById(@Path("movieId") movieId: Int): Response<MovieDetailDto>
}