package com.goesbruno.movieapp.movie_search_feature.presentation.state

import androidx.paging.PagingData
import com.goesbruno.movieapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieSearchUiState(
    val query: String = "",
    val movies: Flow<PagingData<MovieSearch>> = emptyFlow()
)
