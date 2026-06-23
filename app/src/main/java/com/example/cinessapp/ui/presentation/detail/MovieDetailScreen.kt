package com.example.cinessapp.ui.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.cinessapp.R
import com.example.cinessapp.core.state.UiState
import com.example.cinessapp.core.utils.AppConfig
import com.example.cinessapp.core.utils.AppIcons
import com.example.cinessapp.domain.model.Cast
import com.example.cinessapp.domain.model.MovieDetail
import com.example.cinessapp.domain.model.Review
import com.example.cinessapp.ui.presentation.detail.components.CastList
import com.example.cinessapp.ui.presentation.detail.components.DetailHeader
import com.example.cinessapp.ui.presentation.detail.components.ReviewCardListItem
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val movieDetailState by viewModel.movieDetailState.collectAsStateWithLifecycle()
    val castsState by viewModel.castsListState.collectAsStateWithLifecycle()
    val reviewState by viewModel.reviewListState.collectAsStateWithLifecycle()

    Surface(color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val state = movieDetailState) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    }
                }

                is UiState.Error -> {
                    Text(
                        text = "Failed to load detail movie",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                is UiState.Success -> {
                    MovieDetailContent(
                        movieDetail = state.data,
                        castsState = castsState,
                        reviewState = reviewState
                    )
                }

                is UiState.Idle -> Unit
            }
            Row(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = WindowInsets.safeContent.asPaddingValues().calculateTopPadding()
                )
            ) {
                Icon(
                    painterResource(AppIcons.arrowLeft),
                    contentDescription = "Arrow left icon",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .clickable(onClick = onBack)
                )
            }
        }
    }
}

@Composable
fun MovieDetailContent(
    movieDetail: MovieDetail,
    castsState: UiState<List<Cast>>,
    reviewState: UiState<List<Review>>
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${AppConfig.IMAGE_BASE_URL}w500${movieDetail.backdropPath}")
                        .build(),
                    contentDescription = movieDetail.title,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(R.drawable.image_placeholder),
                    error = painterResource(R.drawable.image_placeholder)
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    Icon(
                        painter = painterResource(AppIcons.playCircleBold),
                        contentDescription = "Play circle icon",
                        modifier = Modifier.size(48.dp),
                        tint = MaterialTheme.colorScheme.surfaceVariant
                    )
                }
            }
        }
        DetailHeader(
            title = movieDetail.title,
            year = movieDetail.releaseDate,
            playTime = "play time",
            rating = movieDetail.voteAverage.toString()
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "Overview",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                movieDetail.overview,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            "Casts",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.size(12.dp))
        when (castsState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }
            }

            is UiState.Error -> {
                Text(
                    text = "Failed to load casts list",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            is UiState.Success -> {
                CastList(castsState.data)
            }

            is UiState.Idle -> Unit
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Review",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    "See All",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable(onClick = {})
                )
            }
            when (reviewState) {
                is UiState.Loading -> {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    }
                }

                is UiState.Error -> {
                    Text("Failed to load review", color = MaterialTheme.colorScheme.error)
                }

                is UiState.Success -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(reviewState.data) { item ->
                            ReviewCardListItem(item)
                        }
                    }
                }

                is UiState.Idle -> Unit
            }
        }
    }
}

@Preview
@Composable
private fun MovieDetailScreenPreview() {
    CinessAppTheme() {
//        MovieDetailScreen(
//            movie = Movie(
//                adult = false,
//                backdropPath = "",
//                genreIds = listOf(0),
//                id = 1,
//                oriLanguage = "",
//                oriTitle = "",
//                overview = "This is overview section",
//                popularity = 3.5,
//                posterPath = "",
//                releaseDate = "2025",
//                title = "Title",
//                video = false,
//                voteAverage = 6.8,
//                voteCount = 123
//            ),
//            reviewList = listOf(
//                Review(
//                    author = "Kamal",
//                    authorDetails = Author(
//                        name = "Timal",
//                        userName = "Kamal",
//                        avatarPath = "",
//                        rating = 0,
//                    ),
//                    content = "This movie is awesome",
//                    createdAt = "",
//                    id = "",
//                    updatedAt = "",
//                    url = ""
//                ),
//                Review(
//                    author = "Kamal",
//                    authorDetails = Author(
//                        name = "Timal",
//                        userName = "Kamal",
//                        avatarPath = "",
//                        rating = 0,
//                    ),
//                    content = "This movie is awesome",
//                    createdAt = "",
//                    id = "",
//                    updatedAt = "",
//                    url = ""
//                ),
//            ),
//            castList = listOf(
//                Cast(
//                    profilePath = "",
//                    name = "Brad Pitt",
//                    characterName = "Broadway"
//                ),
//                Cast(
//                    profilePath = "",
//                    name = "Brad Pitt",
//                    characterName = "Broadway"
//                ),
//                Cast(
//                    profilePath = "",
//                    name = "Brad Pitt",
//                    characterName = "Broadway"
//                ),
//                Cast(
//                    profilePath = "",
//                    name = "Brad Pitt",
//                    characterName = "Broadway"
//                ),
//            )
//        )
    }
}