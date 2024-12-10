package com.goesbruno.movieapp.popular_movie_feature.presentation.state

import androidx.paging.PagingData
import com.goesbruno.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PopularMovieUiState(
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)