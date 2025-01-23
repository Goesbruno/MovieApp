package com.goesbruno.movieapp.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.goesbruno.movieapp.core.presentation.navigation.BottomNavigationBar
import com.goesbruno.movieapp.core.presentation.navigation.DetailScreenNav
import com.goesbruno.movieapp.core.presentation.navigation.NavigationGraph
import com.goesbruno.movieapp.core.presentation.navigation.currentRoute


@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold(
        bottomBar = {
            if (currentRoute(navController = navController) != DetailScreenNav.DetailScreen.route) {
                BottomNavigationBar(navController = navController)
            }
        },
        content = {  innerPadding ->
            Box(
                Modifier.padding(innerPadding)
            ) {
                NavigationGraph(navController)
            }

        }
    )
}