package com.example.cinessapp.ui.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cinessapp.ui.presentation.home.components.TopMenu
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        TopMenu()
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    CinessAppTheme() {
        HomeScreen()
    }
}