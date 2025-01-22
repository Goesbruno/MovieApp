package com.goesbruno.movieapp.popular_movie_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.goesbruno.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

//Define o contrato do repositório para obtencão dos filmes de forma paginada
interface PopularMovieRepository {

    fun getPopularMovies(pagingConfig: PagingConfig): Flow<PagingData<Movie>>
}