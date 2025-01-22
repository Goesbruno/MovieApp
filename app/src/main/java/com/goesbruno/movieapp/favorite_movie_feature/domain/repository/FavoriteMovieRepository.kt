package com.goesbruno.movieapp.favorite_movie_feature.domain.repository

import com.goesbruno.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieRepository {

    fun getMovies(): Flow<List<Movie>>

    suspend fun insertMovie(movie: Movie)

    suspend fun delete(movie: Movie)

    suspend fun isFavorite(movieId: Int): Boolean
}