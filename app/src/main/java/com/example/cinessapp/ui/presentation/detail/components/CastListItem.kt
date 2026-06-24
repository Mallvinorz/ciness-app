package com.example.cinessapp.ui.presentation.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.example.cinessapp.domain.model.Cast
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun CastListItem(cast: Cast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(CircleShape)
                .size(56.dp),
            model = ImageRequest.Builder(LocalContext.current)
                .data("${AppConfig.IMAGE_BASE_URL}w185${cast.profilePath}").build(),
            contentDescription = cast.name,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.image_placeholder),
            error = painterResource(R.drawable.image_placeholder)
        )
        Text(cast.name, style = MaterialTheme.typography.labelLarge)
        Text(
            cast.characterName,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun CastListItemPreview() {
    CinessAppTheme() {
        CastListItem(
            cast = Cast(
                profilePath = "",
                name = "Brad Pitt",
                characterName = "Broadway"
            )
        )
    }
}