package com.goesbruno.movieapp.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.goesbruno.movieapp.core.util.Constants.MOVIE_DETAIL_ARGUMENT_KEY

sealed class BottomNavItem (
    val title: String,
    val icon: ImageVector,
    val route: String
) {

    object PopularMovies: BottomNavItem(
        title = "Títulos Populares",
        icon = Icons.Default.Star,
        route = "movie_popular_screen"
    )

    object MovieSearch: BottomNavItem(
        title = "Pesquisar",
        icon = Icons.Default.Search,
        route = "movie_search_screen"
    )

    object FavoriteMovies: BottomNavItem(
        title = "Favoritos",
        icon = Icons.Default.Favorite,
        route = "favorite_movies_screen"
    )

    object MovieDetails: BottomNavItem(
        title = "Detalhes",
        icon = Icons.Default.Info,
        route = "movie_details_destination?$MOVIE_DETAIL_ARGUMENT_KEY=" +
                "{$MOVIE_DETAIL_ARGUMENT_KEY}"
    ) {
        fun passMovieId(movieId: Int) = "movie_details_destination?$MOVIE_DETAIL_ARGUMENT_KEY=$movieId"
    }
}