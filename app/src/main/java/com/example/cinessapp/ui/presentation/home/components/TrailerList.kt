package com.example.cinessapp.ui.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinessapp.domain.model.TrailerItem
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun TrailerList(trailerList: List<TrailerItem>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(trailerList) { item ->
            TrailerListItem(item)
        }
    }
}

@Preview
@Composable
private fun TrailerListPreview() {
    CinessAppTheme() {
        TrailerList(trailerList = listOf(
            TrailerItem(
                trailerPath = "https://unsplash.com/id/s/foto/trailer",
                title = "Trailer",
                trailerType = "Official Trailer"
            ),
            TrailerItem(
                trailerPath = "https://unsplash.com/id/s/foto/trailer",
                title = "Trailer",
                trailerType = "Official Trailer"
            ),
            TrailerItem(
                trailerPath = "https://unsplash.com/id/s/foto/trailer",
                title = "Trailer",
                trailerType = "Official Trailer"
            ),
        ))
    }
}