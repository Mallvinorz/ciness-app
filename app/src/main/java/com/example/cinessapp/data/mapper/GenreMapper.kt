package com.example.cinessapp.data.mapper

import com.example.cinessapp.data.remote.dto.GenreDto
import com.example.cinessapp.data.remote.dto.GenreListDto
import com.example.cinessapp.domain.model.Genre

fun GenreListDto.toDomain(): List<Genre> = genres.map { it.toDomain() }

fun GenreDto.toDomain(): Genre = Genre(
    id = id,
    name = name
)