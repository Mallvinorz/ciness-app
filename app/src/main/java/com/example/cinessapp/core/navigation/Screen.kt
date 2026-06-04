package com.example.cinessapp.core.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
}