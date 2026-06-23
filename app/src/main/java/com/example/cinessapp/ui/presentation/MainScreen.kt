package com.example.cinessapp.ui.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cinessapp.core.navigation.Route
import com.example.cinessapp.ui.presentation.components.BottomBar
import com.example.cinessapp.ui.presentation.detail.MovieDetailScreen
import com.example.cinessapp.ui.presentation.home.HomeScreen
import com.example.cinessapp.ui.presentation.profile.ProfileScreen
import com.example.cinessapp.ui.presentation.search.SearchScreen
import com.example.cinessapp.ui.presentation.watchlist.WatchlistScreen
import com.example.cinessapp.ui.theme.CinessAppTheme
import kotlin.reflect.KClass

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination
    val bottomBarRoutes =
        listOf(Route.Home::class, Route.Search::class, Route.Watchlist::class, Route.Profile::class)
    val showBottomBar = bottomBarRoutes.any {
        currentRoute.isRouteActive(it)
    }

    Scaffold(bottomBar = { if (showBottomBar) BottomBar(navController = navController) }) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Route.Home
        ) {
            composable<Route.Home> {
                HomeScreen(onMovieClick = { movieId ->
                    navController.navigate(
                        Route.MovieDetail(
                            movieId
                        )
                    )
                })
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
                MovieDetailScreen(onBack = { navController.popBackStack() })
            }
        }

    }
}

fun NavDestination?.isRouteActive(route: KClass<*>): Boolean {
    return this?.route?.contains(route.qualifiedName.toString()) == true
}

@Preview
@Composable
private fun MainScreenPreview() {
    CinessAppTheme() {
        MainScreen()
    }
}