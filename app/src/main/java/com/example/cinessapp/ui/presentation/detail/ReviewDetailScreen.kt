package com.example.cinessapp.ui.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinessapp.core.utils.AppIcons
import com.example.cinessapp.domain.model.Author
import com.example.cinessapp.domain.model.Review
import com.example.cinessapp.ui.presentation.detail.components.ReviewCardListItem
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun ReviewDetailScreen(title: String, reviewList: List<Review>) {
    Scaffold(topBar = {
        Surface() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(AppIcons.arrowLeft),
                    contentDescription = "Arrow left icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable(onClick = {})
                )
                Text(
                    "Movies Review",
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Title: $title",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.primary
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(reviewList) { item ->
                    ReviewCardListItem(item)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ReviewDetailScreenPreview() {
    CinessAppTheme() {
        ReviewDetailScreen(
            title = "Man in the black", reviewList = listOf(
                Review(
                    author = "Kamal",
                    authorDetails = Author(
                        name = "Timal",
                        userName = "Kamal",
                        avatarPath = "",
                        rating = 0,
                    ),
                    content = "This movie is awesome",
                    createdAt = "",
                    id = "",
                    updatedAt = "",
                    url = ""
                ),
                Review(
                    author = "Kamal",
                    authorDetails = Author(
                        name = "Timal",
                        userName = "Kamal",
                        avatarPath = "",
                        rating = 0,
                    ),
                    content = "This movie is awesome",
                    createdAt = "",
                    id = "",
                    updatedAt = "",
                    url = ""
                ),
            )
        )
    }
}