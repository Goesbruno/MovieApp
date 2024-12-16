package com.goesbruno.movieapp.movie_search_feature.domain.source

import com.goesbruno.movieapp.core.data.remote.response.SearchResponse
import com.goesbruno.movieapp.core.paging.MovieSearchPagingSource

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource
    suspend fun getSearchedMovies(page: Int, query: String): SearchResponse
}