package com.example.cinessapp.data.repository

import com.example.cinessapp.data.mapper.toDomain
import com.example.cinessapp.data.remote.api.ApiService
import com.example.cinessapp.data.remote.api.NetworkResult
import com.example.cinessapp.data.remote.api.mapSuccess
import com.example.cinessapp.data.remote.api.safeApiCall
import com.example.cinessapp.data.remote.helper.pickBestTrailer
import com.example.cinessapp.domain.model.Cast
import com.example.cinessapp.domain.model.Genre
import com.example.cinessapp.domain.model.MovieDetail
import com.example.cinessapp.domain.model.MovieList
import com.example.cinessapp.domain.model.Review
import com.example.cinessapp.domain.model.VideoTrailer
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

    override suspend fun getDetailMovieById(movieId: Int): NetworkResult<MovieDetail> =
        safeApiCall {
            apiService.getMovieById(movieId)
        }.mapSuccess { it.toDomain() }

    override suspend fun getMovieCreditsById(movieId: Int): NetworkResult<List<Cast>> =
        safeApiCall {
            apiService.getMovieCreditsById(movieId)
        }.mapSuccess { it.toDomain() }

    override suspend fun getMovieReviewsById(movieId: Int): NetworkResult<List<Review>> =
        safeApiCall {
            apiService.getMovieReviewsById(movieId)
        }.mapSuccess { it.toDomain() }

    override suspend fun getDetailMovideVideosById(movieId: Int): NetworkResult<VideoTrailer> {
        val result = safeApiCall { apiService.getDetailMovieVideos(movieId) }
        return when (result) {
            is NetworkResult.Success -> {
                val bestVideo = result.data.results.pickBestTrailer()?.toDomain()
                if (bestVideo != null) {
                    NetworkResult.Success(bestVideo)
                } else {
                    NetworkResult.Exception(NoSuchElementException("No Trailer Available"))
                }
            }

            is NetworkResult.Exception -> result
            is NetworkResult.HttpError -> result
        }
    }
}