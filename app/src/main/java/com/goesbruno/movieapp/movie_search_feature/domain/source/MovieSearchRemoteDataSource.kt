package com.goesbruno.movieapp.movie_search_feature.domain.source

import com.goesbruno.movieapp.core.domain.model.MovieSearchPaging
import com.goesbruno.movieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchedMovies(page: Int, query: String): MovieSearchPaging
}