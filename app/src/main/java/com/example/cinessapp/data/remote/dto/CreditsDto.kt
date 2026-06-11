package com.example.cinessapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CreditsDto(
    val id: Int,
    val cast: List<CastDto>
)

data class CastDto(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    @SerializedName("known_for_department") val department: String,
    val name: String,
    @SerializedName("original_name") val originalName: String,
    val popularity: Double,
    @SerializedName("profile_path") val profilePath: String,
    @SerializedName("cast_id") val castId: String,
    val character: String,
)