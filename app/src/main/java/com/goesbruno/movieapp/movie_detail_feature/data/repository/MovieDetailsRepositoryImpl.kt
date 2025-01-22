package com.goesbruno.movieapp.movie_detail_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.domain.model.MovieDetails
import com.goesbruno.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.goesbruno.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieDetailRemoteDataSource
) : MovieDetailsRepository{

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteDataSource.getMovieDetail(movieId)
    }

    override suspend fun getSimilarMovies(
        movieId: Int,
        pagingConfig: PagingConfig
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getSimilarMoviesPagingSource(movieId = movieId)
            }
        ).flow
    }
}