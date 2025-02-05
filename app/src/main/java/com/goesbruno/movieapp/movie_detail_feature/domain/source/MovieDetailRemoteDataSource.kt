package com.goesbruno.movieapp.movie_detail_feature.domain.source

import com.goesbruno.movieapp.core.domain.model.MovieDetails
import com.goesbruno.movieapp.core.domain.model.MoviePaging
import com.goesbruno.movieapp.core.paging.SimilarMoviePagingSource

interface MovieDetailRemoteDataSource {

    suspend fun getMovieDetail(movieId: Int): MovieDetails
    suspend fun getSimilarMovies(page: Int, movieId: Int): MoviePaging
    fun getSimilarMoviesPagingSource(movieId: Int): SimilarMoviePagingSource

}