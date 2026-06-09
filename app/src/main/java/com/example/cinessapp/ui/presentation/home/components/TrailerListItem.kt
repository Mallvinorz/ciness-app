package com.example.cinessapp.ui.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.cinessapp.R
import com.example.cinessapp.core.utils.AppIcons
import com.example.cinessapp.domain.model.TrailerItem
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun TrailerListItem(item: TrailerItem) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current).data(item.trailerPath)
                    .crossfade(true).build(),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.image_placeholder),
                error = painterResource(R.drawable.image_placeholder)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )
            Icon(
                painter = painterResource(AppIcons.playCircleBold),
                contentDescription = "Play circle icon",
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.surfaceVariant
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            item.title,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            item.trailerType, style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
private fun TrailerListItemPreview() {
    CinessAppTheme() {
        TrailerListItem(
            item = TrailerItem(
                trailerPath = "https://unsplash.com/id/s/foto/trailer",
                title = "Trailer",
                trailerType = "Official Trailer"
            )
        )
    }
}