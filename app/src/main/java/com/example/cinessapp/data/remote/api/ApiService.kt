package com.example.cinessapp.data.remote.api

import com.example.cinessapp.data.remote.dto.MovieListDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(): Response<MovieListDto>
}