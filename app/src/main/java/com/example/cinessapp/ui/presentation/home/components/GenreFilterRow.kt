package com.example.cinessapp.ui.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinessapp.domain.model.Genre

@Composable
fun GenreFilterRow(genres: List<Genre>, selectedGenre: Genre?, onGenreSelected: (Genre) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(genres, key = { it.id }) {genre ->
            GenreFilterChip(
                genre = genre,
                isSelected = selectedGenre?.id == genre.id,
                onSelected = onGenreSelected
            )
        }
    }

}