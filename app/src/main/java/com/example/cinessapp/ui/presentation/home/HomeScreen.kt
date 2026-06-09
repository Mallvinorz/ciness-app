package com.example.cinessapp.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cinessapp.ui.presentation.home.components.TopMenu
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopMenu()
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            "Latest Trailer",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(20.dp))

        Spacer(modifier = Modifier.size(8.dp))
        Text(
            "Trending Now",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(20.dp))

        Spacer(modifier = Modifier.size(8.dp))
        Text(
            "Popular",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(20.dp))
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CinessAppTheme() {
        HomeScreen()
    }
}