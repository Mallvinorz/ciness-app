package com.example.cinessapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cinessapp.presentation.home.components.TopMenu
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun HomeScreen() {
    Column() {
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