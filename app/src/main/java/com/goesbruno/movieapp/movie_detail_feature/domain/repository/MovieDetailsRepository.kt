package com.goesbruno.movieapp.movie_detail_feature.domain.repository

import androidx.paging.PagingSource
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.domain.model.MovieDetails

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: Int): MovieDetails
    fun getSimilarMovies(movieId: Int): PagingSource<Int, Movie>

}