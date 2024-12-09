package com.goesbruno.movieapp.core.presentation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.goesbruno.movieapp.core.presentation.navigation.BottomNavigationBar
import com.goesbruno.movieapp.core.presentation.navigation.NavigationGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        content = {  innerPadding ->
            NavigationGraph(navController)

        }
    )
}