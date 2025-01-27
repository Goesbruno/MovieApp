package com.goesbruno.movieapp.favorite_movie_feature.presentation.state

import com.goesbruno.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FavoriteMovieUiState(
    val movies: Flow<List<Movie>> = emptyFlow()
)
