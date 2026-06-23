package com.example.cinessapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthorDto(
    val name: String,
    val username: String,
    @SerializedName("avatar_path") val avatarPath: String,
    val rating: Int?
)
