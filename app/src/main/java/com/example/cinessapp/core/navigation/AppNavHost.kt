package com.example.cinessapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinessapp.ui.presentation.detail.MovieDetailScreen
import com.example.cinessapp.ui.presentation.detail.ReviewDetailScreen
import com.example.cinessapp.ui.presentation.home.HomeScreen
import com.example.cinessapp.ui.presentation.profile.ProfileScreen
import com.example.cinessapp.ui.presentation.search.SearchScreen
import com.example.cinessapp.ui.presentation.watchlist.WatchlistScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            HomeScreen()
        }
        composable<Route.Search> {
            SearchScreen()
        }
        composable<Route.Watchlist> {
            WatchlistScreen()
        }
        composable<Route.Profile> {
            ProfileScreen()
        }
        composable<Route.MovieDetail> {
            MovieDetailScreen()
        }
        composable<Route.ReviewDetail> {
            ReviewDetailScreen()
        }
    }
}