package com.goesbruno.movieapp.popular_movie_feature.domain.repository

import androidx.paging.PagingSource
import com.goesbruno.movieapp.core.domain.model.Movie


interface PopularMovieRepository {

    fun getPopularMovies(): PagingSource<Int, Movie>
}