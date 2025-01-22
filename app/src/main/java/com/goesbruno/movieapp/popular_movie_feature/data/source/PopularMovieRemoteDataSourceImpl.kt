package com.goesbruno.movieapp.popular_movie_feature.data.source

import com.goesbruno.movieapp.core.data.remote.MovieService
import com.goesbruno.movieapp.core.data.remote.response.MovieResponse
import com.goesbruno.movieapp.core.paging.MoviePagingSource
import com.goesbruno.movieapp.popular_movie_feature.domain.source.PopularMovieRemoteDataSource
import javax.inject.Inject


class PopularMovieRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): PopularMovieRemoteDataSource {
    override suspend fun getPopularMovies(page: Int): MovieResponse {
        return service.getPopularMovies(page = page)
    }

    override fun getPopularMoviesPagingSource(): MoviePagingSource {
        return MoviePagingSource(remoteDataSource = this)
    }
}