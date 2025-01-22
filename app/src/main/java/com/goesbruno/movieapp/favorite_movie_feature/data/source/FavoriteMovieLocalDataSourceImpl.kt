package com.goesbruno.movieapp.favorite_movie_feature.data.source

import com.goesbruno.movieapp.core.data.local.dao.MovieDao
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.favorite_movie_feature.data.mapper.toMovieEntity
import com.goesbruno.movieapp.favorite_movie_feature.data.mapper.toMovies
import com.goesbruno.movieapp.favorite_movie_feature.domain.source.FavoriteMovieLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteMovieLocalDataSourceImpl @Inject constructor(
    private val dao: MovieDao
): FavoriteMovieLocalDataSource {

    override fun getMovies(): Flow<List<Movie>> {
        return dao.getMovies().map {
            it.toMovies()
        }
    }

    override suspend fun insertMovie(movie: Movie) {
        dao.insertMovie(movie.toMovieEntity())
    }

    override suspend fun delete(movie: Movie) {
        dao.deleteMovie(movie.toMovieEntity())
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return dao.isFavorite(movieId) != null
    }
}