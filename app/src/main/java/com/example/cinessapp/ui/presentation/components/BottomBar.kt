package com.example.cinessapp.ui.presentation.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cinessapp.R
import com.example.cinessapp.core.navigation.Route
import com.example.cinessapp.core.utils.AppIcons
import com.example.cinessapp.domain.model.BottomBarItem
import com.example.cinessapp.ui.theme.CinessAppTheme

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Surface(shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.surface,
        ) {
            val bottomBar = listOf(
                BottomBarItem(
                    title = "Home",
                    icon = AppIcons.home,
                    selectedIcon = AppIcons.homeBold,
                    route = Route.Home
                ),
                BottomBarItem(
                    title = "Search",
                    icon = AppIcons.searchNormal,
                    selectedIcon = AppIcons.searchNormalBold,
                    route = Route.Search
                ),
                BottomBarItem(
                    title = "Wishlist",
                    icon = AppIcons.bookmark,
                    selectedIcon = AppIcons.bookmarkBold,
                    route = Route.Watchlist
                ),
                BottomBarItem(
                    title = "Profile",
                    icon = AppIcons.profile,
                    selectedIcon = AppIcons.profileBold,
                    route = Route.Profile
                )
            )

            bottomBar.forEach {
                val isSelected = currentRoute == it.route::class.qualifiedName

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(it.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(if (isSelected) it.selectedIcon else it.icon),
                            contentDescription = it.title
                        )
                    },
                    label = {
                        Text(
                            it.title,
                            style = if (isSelected) MaterialTheme.typography.labelMedium else MaterialTheme.typography.labelSmall,
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = Color.Transparent
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    CinessAppTheme() {
        BottomBar(rememberNavController())
    }
}