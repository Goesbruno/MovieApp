package com.goesbruno.movieapp.favorite_movie_feature.domain.source

import com.goesbruno.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieLocalDataSource {

    fun getMovies(): Flow<List<Movie>>

    suspend fun insertMovie(movie: Movie)

    suspend fun delete(movie: Movie)

    suspend fun isFavorite(movieId: Int): Boolean

}