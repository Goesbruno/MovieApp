package com.goesbruno.movieapp.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.goesbruno.movieapp.core.util.Constants
import com.goesbruno.movieapp.favorite_movie_feature.presentation.FavoriteMoviesScreen
import com.goesbruno.movieapp.favorite_movie_feature.presentation.FavoriteMoviesViewModel
import com.goesbruno.movieapp.movie_detail_feature.presentation.MovieDetailsScreen
import com.goesbruno.movieapp.movie_detail_feature.presentation.MovieDetailsViewModel
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
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
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
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                }
            )
        }
        composable(BottomNavItem.FavoriteMovies.route){

        }

        composable(
            route = DetailScreenNav.DetailScreen.route,
            arguments = listOf(
                navArgument(Constants.MOVIE_DETAIL_ARGUMENT_KEY) {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){ it: NavBackStackEntry ->
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()
            val onAddFavorite = viewModel::onAddFavorite

            MovieDetailsScreen(
                uiState = uiState,
                onSimilarMovieClick = {
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it.id))
                },
                onAddFavorite = onAddFavorite,
            )
        }

        composable(BottomNavItem.FavoriteMovies.route){
            val viewModel: FavoriteMoviesViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle()
            val movies by uiState.value.movies.collectAsStateWithLifecycle(initialValue = emptyList())


            FavoriteMoviesScreen(
                movies = movies,
                navigateToMovieDetail = {
                    navController.navigate(DetailScreenNav.DetailScreen.passMovieId(movieId = it))
                }
            )
        }

    }

}