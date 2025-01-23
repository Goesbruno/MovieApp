package com.goesbruno.movieapp.core.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.goesbruno.movieapp.ui.theme.GreyDark
import com.goesbruno.movieapp.ui.theme.black
import com.goesbruno.movieapp.ui.theme.yellow

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.PopularMovies,
        BottomNavItem.MovieSearch,
        BottomNavItem.FavoriteMovies
    )

    NavigationBar(
        containerColor = black
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState().value
        val currentRout = navBackStackEntry?.destination?.route

        items.forEach{ destination ->
            NavigationBarItem(
                selected = currentRout == destination.route,
                onClick = {
                    navController.navigate(destination.route) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = destination.icon, contentDescription = null)
                },
                label = {
                    Text(
                        text = destination.title
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = yellow,
                    selectedTextColor = yellow,
                    unselectedIconColor = yellow,
                    unselectedTextColor = yellow,
                    indicatorColor = GreyDark
                )
            )
        }
    }

}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}


