package com.goesbruno.movieapp.popular_movie_feature.data.source

import com.goesbruno.movieapp.core.data.remote.MovieService
import com.goesbruno.movieapp.core.domain.model.MoviePaging
import com.goesbruno.movieapp.core.paging.MoviePagingSource
import com.goesbruno.movieapp.popular_movie_feature.data.mapper.toMovie
import com.goesbruno.movieapp.popular_movie_feature.domain.source.PopularMovieRemoteDataSource
import javax.inject.Inject


class PopularMovieRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): PopularMovieRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): MoviePaging {
        val response = service.getPopularMovies(page)
        return MoviePaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.results.map { it.toMovie() }
        )

    }

    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(remoteDataSource = this)
    }
}