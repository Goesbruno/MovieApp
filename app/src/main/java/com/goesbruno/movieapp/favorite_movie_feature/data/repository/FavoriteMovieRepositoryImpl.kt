package com.goesbruno.movieapp.favorite_movie_feature.data.repository

import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.favorite_movie_feature.domain.repository.FavoriteMovieRepository
import com.goesbruno.movieapp.favorite_movie_feature.domain.source.FavoriteMovieLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor(
    private val localDataSource: FavoriteMovieLocalDataSource
): FavoriteMovieRepository {

    override fun getMovies(): Flow<List<Movie>> {
        return localDataSource.getMovies()
    }

    override suspend fun insertMovie(movie: Movie) {
        localDataSource.insertMovie(movie)
    }

    override suspend fun delete(movie: Movie) {
        localDataSource.delete(movie)
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return localDataSource.isFavorite(movieId)
    }
}