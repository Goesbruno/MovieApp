package com.goesbruno.movieapp.movie_search_feature.data.source

import com.goesbruno.movieapp.core.data.remote.MovieService
import com.goesbruno.movieapp.core.data.remote.response.SearchResponse
import com.goesbruno.movieapp.core.paging.MovieSearchPagingSource
import com.goesbruno.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor (
    private val service: MovieService
): MovieSearchRemoteDataSource {
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource {
        return MovieSearchPagingSource(query = query, remoteDataSource = this)
    }

    override suspend fun getSearchedMovies(
        page: Int,
        query: String
    ): SearchResponse {
        return service.searchMovie(page = page, query = query)
    }
}