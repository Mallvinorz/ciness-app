package com.example.cinessapp.data.remote.api

import com.example.cinessapp.data.remote.dto.CreditsDto
import com.example.cinessapp.data.remote.dto.GenreListDto
import com.example.cinessapp.data.remote.dto.MovieDetailDto
import com.example.cinessapp.data.remote.dto.MovieListDto
import com.example.cinessapp.data.remote.dto.ReviewDto
import com.example.cinessapp.data.remote.dto.VideoTrailerDto
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

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCreditsById(@Path("movieId") movieId: Int): Response<CreditsDto>

    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReviewsById(@Path("movieId") movieId: Int): Response<ReviewDto>

    @GET("movie/{movieId}/videos")
    suspend fun getDetailMovieVideos(@Path("movieId") movieId: Int): Response<VideoTrailerDto>
}