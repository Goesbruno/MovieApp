package com.goesbruno.movieapp.popular_movie_feature.domain.source

import com.goesbruno.movieapp.core.data.remote.response.MovieResponse
import com.goesbruno.movieapp.core.paging.MoviePagingSource

interface PopularMovieRemoteDataSource {

    suspend fun getPopularMovies(page: Int): MovieResponse

    fun getPopularMoviesPagingSource(): MoviePagingSource

}