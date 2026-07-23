package com.example.cinessapp.data.mapper

import com.example.cinessapp.data.remote.dto.VideoTrailerResultsDto
import com.example.cinessapp.domain.model.VideoTrailer

fun VideoTrailerResultsDto.toDomain(): VideoTrailer = VideoTrailer(
    name = name,
    key = key,
    site = site
)