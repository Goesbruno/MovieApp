package com.goesbruno.movieapp.movie_search_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.goesbruno.movieapp.core.domain.model.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository {
    fun getSearchedMovies(query: String, pagingConfig: PagingConfig): Flow<PagingData<MovieSearch>>
}