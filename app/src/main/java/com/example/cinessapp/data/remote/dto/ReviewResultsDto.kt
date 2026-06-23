package com.example.cinessapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    val id: Int,
    val page: Int,
    val results: List<ReviewResultsDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)

data class ReviewResultsDto(
    val author: String,
    @SerializedName("author_details") val authorDetails: AuthorDto,
    val content: String,
    @SerializedName("created_at") val createdAt: String,
    val id: String,
    @SerializedName("updated_at") val updatedAt: String,
    val url: String
)
