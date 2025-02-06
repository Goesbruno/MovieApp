package com.goesbruno.movieapp.popular_movie_feature.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.popular_movie_feature.domain.repository.PopularMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface GetPopularMoviesUseCase {
    operator fun invoke(params: Params): Flow<PagingData<Movie>>
    data class Params(val pagingConfig: PagingConfig)
}

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: PopularMovieRepository
): GetPopularMoviesUseCase {

    override fun invoke(params: GetPopularMoviesUseCase.Params): Flow<PagingData<Movie>> {
        return try {
            val pagingSource = repository.getPopularMovies()
            Pager(
                config = params.pagingConfig,
                pagingSourceFactory = {
                    pagingSource
                }
            ).flow

        } catch (e: Exception) {
            emptyFlow()
        }
    }
}