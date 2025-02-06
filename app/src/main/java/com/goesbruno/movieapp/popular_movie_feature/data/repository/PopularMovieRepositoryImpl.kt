package com.goesbruno.movieapp.popular_movie_feature.data.repository

import androidx.paging.PagingSource
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.paging.MoviePagingSource
import com.goesbruno.movieapp.popular_movie_feature.domain.repository.PopularMovieRepository
import com.goesbruno.movieapp.popular_movie_feature.domain.source.PopularMovieRemoteDataSource

class PopularMovieRepositoryImpl(
    private val remoteDataSource: PopularMovieRemoteDataSource
) : PopularMovieRepository {
    override fun getPopularMovies(): PagingSource<Int, Movie> {
        return MoviePagingSource(remoteDataSource)
    }
}