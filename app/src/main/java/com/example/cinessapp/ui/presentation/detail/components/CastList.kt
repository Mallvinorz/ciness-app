package com.example.cinessapp.ui.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinessapp.domain.model.Cast
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun CastList(castList: List<Cast>) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(castList) { item ->
            CastListItem(item)
        }
    }
}

@Preview
@Composable
private fun CastListPreview() {
    CinessAppTheme() {
        CastList(
            castList = listOf(
                Cast(
                    profilePath = "",
                    name = "Brad Pitt",
                    characterName = "Broadway"
                ),
                Cast(
                    profilePath = "",
                    name = "Brad Pitt",
                    characterName = "Broadway"
                ),
                Cast(
                    profilePath = "",
                    name = "Brad Pitt",
                    characterName = "Broadway"
                ),
                Cast(
                    profilePath = "",
                    name = "Brad Pitt",
                    characterName = "Broadway"
                ),
            )
        )
    }
}