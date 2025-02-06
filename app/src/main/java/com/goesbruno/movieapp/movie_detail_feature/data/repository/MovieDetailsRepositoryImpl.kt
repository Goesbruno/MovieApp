package com.goesbruno.movieapp.movie_detail_feature.data.repository

import androidx.paging.PagingSource
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.domain.model.MovieDetails
import com.goesbruno.movieapp.core.paging.SimilarMoviePagingSource
import com.goesbruno.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.goesbruno.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailRemoteDataSource
) : MovieDetailsRepository{

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteDataSource.getMovieDetail(movieId)
    }

    override fun getSimilarMovies(
        movieId: Int
    ): PagingSource<Int, Movie> {
        return SimilarMoviePagingSource(
            movieId = movieId,
            remoteDataSource = remoteDataSource
        )
    }
}