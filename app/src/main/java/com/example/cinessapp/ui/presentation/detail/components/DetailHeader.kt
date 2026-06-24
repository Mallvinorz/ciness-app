package com.example.cinessapp.ui.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinessapp.core.utils.AppIcons
import com.example.cinessapp.domain.model.Genre
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun DetailHeader(
    title: String,
    year: String,
    playTime: String,
    rating: String,
    genres: List<Genre>
) {
    Surface() {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        title,
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            year,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        VerticalDivider(modifier = Modifier.height(16.dp))
                        Text(
                            playTime,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        VerticalDivider(modifier = Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            Icon(
                                painterResource(AppIcons.magicStarBold),
                                contentDescription = "Star icon",
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                rating,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
                Icon(
                    painter = painterResource(AppIcons.heart),
                    contentDescription = "Save icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.clickable(onClick = {})
                )
            }
            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                items(genres) { item ->
                    GenreItem(item.name)
                }
            }
        }
    }
}

@Composable
fun GenreItem(genreItem: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(50))
            .background(color = MaterialTheme.colorScheme.inverseSurface)
    ) {
        Text(
            text = genreItem,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview
@Composable
private fun DetailHeaderPreview() {
    CinessAppTheme() {
        DetailHeader(
            title = "Man in the black",
            year = "2026",
            playTime = "3h 2m",
            rating = "8.8",
            genres = listOf(Genre(
                id = 1,
                name = "Action"
            ), Genre(
                id = 2,
                name = "Thriller"
            )
            )
        )
    }
}