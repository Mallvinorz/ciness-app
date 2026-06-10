package com.example.cinessapp.ui.presentation.home.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinessapp.domain.model.Genre
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun GenreFilterChip(
    genre: Genre,
    isSelected: Boolean,
    onSelected: (Genre) -> Unit,
) {
    FilterChip(
        selected = isSelected,
        onClick = { onSelected(genre) },
        label = {
            Text(text = genre.name, style = MaterialTheme.typography.bodySmall)
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.surface,
            labelColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Preview
@Composable
private fun GenreFilterChipPreview() {
    CinessAppTheme() {
//        GenreFilterChip(genre = "Action", isSelected = true, onSelected = {})
    }
}