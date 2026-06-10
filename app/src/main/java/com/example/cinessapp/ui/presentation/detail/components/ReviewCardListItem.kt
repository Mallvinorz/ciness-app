package com.example.cinessapp.ui.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.cinessapp.R
import com.example.cinessapp.core.utils.AppConfig
import com.example.cinessapp.domain.model.Author
import com.example.cinessapp.domain.model.Review
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun ReviewCardListItem(review: Review) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 12.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(AppConfig.BASE_URL + review.authorDetails.avatarPath).build(),
                    contentDescription = "${review.author} review",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.image_placeholder),
                    error = painterResource(R.drawable.image_placeholder)
                )
                Text(
                    review.author,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                review.content,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Composable
private fun ReviewCardListItemPreview() {
    CinessAppTheme() {
        ReviewCardListItem(
            review = Review(
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
            )
        )
    }
}