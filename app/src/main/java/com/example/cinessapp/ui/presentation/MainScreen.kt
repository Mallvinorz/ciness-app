package com.example.cinessapp.ui.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.cinessapp.core.navigation.AppNavHost
import com.example.cinessapp.ui.presentation.components.BottomBar
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomBar(navController = navController) }) { innerPadding ->
        AppNavHost(modifier = Modifier.padding(innerPadding), navController = navController)
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    CinessAppTheme() {
        MainScreen()
    }
}