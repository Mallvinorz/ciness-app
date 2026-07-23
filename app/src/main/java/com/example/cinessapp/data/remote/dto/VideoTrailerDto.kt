package com.example.cinessapp.data.remote.dto

data class VideoTrailerDto(
    val id: Int,
    val results: List<VideoTrailerResultsDto>
)

data class VideoTrailerResultsDto(
    val name: String,
    val key: String,
    val site: String,
    val type: String,
    val official: Boolean
)
