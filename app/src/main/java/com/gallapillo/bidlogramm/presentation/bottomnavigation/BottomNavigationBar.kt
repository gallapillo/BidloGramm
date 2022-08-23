package com.gallapillo.bidlogramm.presentation.bottomnavigation

import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Accessible
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gallapillo.bidlogramm.presentation.theme.MainColor
import com.gallapillo.bidlogramm.presentation.theme.MainColorDark

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Feeds : NavigationItem("feeds_screen", Icons.Default.Menu, "Feeds")
    object Search : NavigationItem("search_screen",Icons.Default.Search, "Search")
    object Profile : NavigationItem("profile_Screen", Icons.Default.Person, "Profile")
}

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        NavigationItem.Feeds,
        NavigationItem.Search,
        NavigationItem.Profile,
    )
    NavigationBar(
        containerColor = MainColor,
        contentColor = Color.Black,
    ) {
        items.forEach { item ->
            NavigationBarItem(
                icon = { Image(imageVector = item.icon, contentDescription = item.title, colorFilter = ColorFilter.tint(Color.Black)) },
                label = { Text(text = item.title, color = Color.Black) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(indicatorColor = MainColorDark),
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
