package com.goesbruno.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.goesbruno.movieapp.movie_search_feature.presentation.MovieSearchEvent
import com.goesbruno.movieapp.movie_search_feature.presentation.MovieSearchScreen
import com.goesbruno.movieapp.movie_search_feature.presentation.MovieSearchViewModel
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
            val viewModel: MovieSearchViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsState().value
            val onEvent: (MovieSearchEvent) -> Unit = viewModel::event
            val onFetch: (String) -> Unit = viewModel::fetch

            MovieSearchScreen(
                uiState = uiState,
                onEvent = onEvent,
                onFetch = onFetch,
                navigateToMovieDetail = {

                }
            )
        }
        composable(BottomNavItem.FavoriteMovies.route){

        }
    }

}