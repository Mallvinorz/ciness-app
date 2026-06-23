package com.example.cinessapp.data.mapper

import com.example.cinessapp.data.remote.dto.AuthorDto
import com.example.cinessapp.data.remote.dto.ReviewDto
import com.example.cinessapp.data.remote.dto.ReviewResultsDto
import com.example.cinessapp.domain.model.Author
import com.example.cinessapp.domain.model.Review

fun ReviewDto.toDomain(): List<Review> = results.map { it.toDomain() }
fun ReviewResultsDto.toDomain(): Review = Review(
    author = author,
    authorDetails = authorDetails.toDomain(),
    content = content,
    createdAt = createdAt,
    id = id,
    updatedAt = updatedAt,
    url = url
)

fun AuthorDto.toDomain(): Author = Author(
    name = name,
    userName = username,
    avatarPath = avatarPath,
    rating = rating
)