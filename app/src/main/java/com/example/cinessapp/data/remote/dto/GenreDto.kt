package com.example.cinessapp.data.remote.dto

data class GenreDto(
    val id: Int,
    val name: String
)

data class GenreListDto(
    val genres: List<GenreDto>
)
