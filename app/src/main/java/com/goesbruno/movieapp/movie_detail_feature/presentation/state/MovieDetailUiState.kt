package com.goesbruno.movieapp.movie_detail_feature.presentation.state

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieDetailUiState(
    val movieDetails: MovieDetails? = null,
    val error: String = "",
    val isLoading: Boolean = false,
    val iconColor: Color = Color.White,
    val results: Flow<PagingData<Movie>> = emptyFlow()
)
