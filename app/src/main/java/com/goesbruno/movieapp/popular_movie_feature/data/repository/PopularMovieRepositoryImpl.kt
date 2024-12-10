package com.goesbruno.movieapp.popular_movie_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.popular_movie_feature.domain.repository.PopularMovieRepository
import com.goesbruno.movieapp.popular_movie_feature.domain.source.PopularMovieRemoteDataSource
import kotlinx.coroutines.flow.Flow

class PopularMovieRepositoryImpl(
    private val remoteDataSource: PopularMovieRemoteDataSource
) : PopularMovieRepository {
    override fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                remoteDataSource.getPopularMoviesPagingSource()
            }
        ).flow
    }
}