package com.example.cinessapp.data.mapper

import com.example.cinessapp.data.remote.dto.CastDto
import com.example.cinessapp.data.remote.dto.CreditsDto
import com.example.cinessapp.domain.model.Cast

fun CreditsDto.toDomain(): List<Cast> = cast.map { it.toDomain() }

fun CastDto.toDomain(): Cast = Cast(
    profilePath = profilePath ?: "",
    name = name,
    characterName = character
)