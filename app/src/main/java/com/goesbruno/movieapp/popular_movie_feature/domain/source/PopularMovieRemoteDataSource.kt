package com.goesbruno.movieapp.popular_movie_feature.domain.source

import com.goesbruno.movieapp.core.domain.model.MoviePaging
import com.goesbruno.movieapp.core.paging.MoviePagingSource

//Define um contrato para a fonte de dados remota que fornece os filmes populares
interface PopularMovieRemoteDataSource {

    //Obtém a resposta de filmes de uma determinada página
    suspend fun getPopularMovies(page: Int): MoviePaging

    //Retorna uma instância do PagingSource
    fun getPopularMoviesPagingSource(): MoviePagingSource

}