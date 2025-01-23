package com.goesbruno.movieapp.core.presentation.navigation

import com.goesbruno.movieapp.core.util.Constants.MOVIE_DETAIL_ARGUMENT_KEY

sealed class DetailScreenNav(
    val route: String
) {
    object DetailScreen: DetailScreenNav(
        route = "movie_details_destination?$MOVIE_DETAIL_ARGUMENT_KEY=" +
                "{$MOVIE_DETAIL_ARGUMENT_KEY}"
    ) {
        fun passMovieId(movieId: Int) = "movie_details_destination?$MOVIE_DETAIL_ARGUMENT_KEY=$movieId"
    }
}