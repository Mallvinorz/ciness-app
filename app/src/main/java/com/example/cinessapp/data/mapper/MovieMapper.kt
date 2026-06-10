package com.example.cinessapp.data.mapper

import com.example.cinessapp.data.remote.dto.MovieDto
import com.example.cinessapp.data.remote.dto.MovieListDto
import com.example.cinessapp.domain.model.Movie
import com.example.cinessapp.domain.model.MovieList

fun MovieListDto.toDomain(): MovieList = MovieList(
    page = page,
    results = results.map { it.toDomain() },
    totalPages = totalPages,
    totalResults = totalResults
)

fun MovieDto.toDomain(): Movie = Movie(
    adult = adult,
    backdropPath = backdropPath ?: "",
    genreIds = genreIds,
    id = id,
    oriLanguage = originalLanguage,
    oriTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)