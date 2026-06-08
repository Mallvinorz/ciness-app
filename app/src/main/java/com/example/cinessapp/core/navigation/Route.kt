package com.example.cinessapp.core.navigation

import kotlinx.serialization.Serializable

//sealed class Route(val route: String) {
//    object Home : Route("home")
//    object Search : Route("search")
//    object Watchlist : Route("watchlist")
//    object Profile : Route("profile")
//}

sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data object Search : Route

    @Serializable
    data object Watchlist : Route

    @Serializable
    data object Profile : Route
}