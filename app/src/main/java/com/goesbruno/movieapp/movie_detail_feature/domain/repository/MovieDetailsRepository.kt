package com.goesbruno.movieapp.movie_detail_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: Int): MovieDetails
    suspend fun getSimilarMovies(movieId: Int, pagingConfig: PagingConfig): Flow<PagingData<Movie>>

}