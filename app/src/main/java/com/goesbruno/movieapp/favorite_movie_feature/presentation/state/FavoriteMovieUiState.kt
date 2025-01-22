package com.goesbruno.movieapp.favorite_movie_feature.presentation.state

import com.goesbruno.movieapp.core.domain.model.Movie

data class FavoriteMovieUiState(
    val movies: List<Movie> = emptyList()
)
