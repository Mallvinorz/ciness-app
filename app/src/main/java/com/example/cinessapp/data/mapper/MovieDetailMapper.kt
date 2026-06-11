package com.example.cinessapp.data.mapper

import com.example.cinessapp.data.remote.dto.MovieDetailDto
import com.example.cinessapp.domain.model.MovieDetail

fun MovieDetailDto.toDomain(): MovieDetail = MovieDetail(
    adult = adult,
    backdropPath = backdropPath,
    budget = budget,
    genres = genres.map { it.toDomain() },
    homepage = homepage,
    id = id,
    imdbId = imdbId,
    oriLanguage = originalLanguage,
    oriTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    posterPath = posterPath,
    releaseDate = releaseDate,
    revenue = revenue,
    runtime = runtime,
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    voteAverage = voteAverage,
    voteCount = voteCount
)