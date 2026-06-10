package com.example.cinessapp.domain.model

data class Review(
    val author: String,
    val authorDetails: Author,
    val content: String,
    val createdAt: String,
    val id: String,
    val updatedAt: String,
    val url: String
)
