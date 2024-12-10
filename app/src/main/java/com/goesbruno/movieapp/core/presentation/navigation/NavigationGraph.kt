package com.goesbruno.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.goesbruno.movieapp.popular_movie_feature.PopularMoviesScreen
import com.goesbruno.movieapp.popular_movie_feature.presentation.PopularMovieViewModel


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.PopularMovies.route
    ) {

        composable(BottomNavItem.PopularMovies.route){
            val viewModel: PopularMovieViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsState().value

            PopularMoviesScreen(
                uiState = uiState,
                navigateToMovieDetail = {

                },
            )
        }
        composable(BottomNavItem.MovieSearch.route){

        }
        composable(BottomNavItem.FavoriteMovies.route){

        }
    }

}