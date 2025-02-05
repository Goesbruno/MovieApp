package com.goesbruno.movieapp.movie_search_feature.data.source

import com.goesbruno.movieapp.core.data.remote.MovieService
import com.goesbruno.movieapp.core.domain.model.MovieSearchPaging
import com.goesbruno.movieapp.core.paging.MovieSearchPagingSource
import com.goesbruno.movieapp.movie_search_feature.data.mapper.toMovieSearch
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
    ): MovieSearchPaging {

        val response = service.searchMovie(page = page, query = query)
        return MovieSearchPaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.results.map { it.toMovieSearch() }
        )
    }
}