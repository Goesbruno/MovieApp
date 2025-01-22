package com.goesbruno.movieapp.favorite_movie_feature.domain.usecase

import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.favorite_movie_feature.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetFavoriteMoviesUseCase {

    suspend operator fun invoke(): Flow<List<Movie>>

}


class  GetFavoriteMoviesUseCaseImpl @Inject constructor(
    private val favoriteMovieRepository: FavoriteMovieRepository
): GetFavoriteMoviesUseCase {

    override suspend fun invoke(): Flow<List<Movie>> {
        return favoriteMovieRepository.getMovies()
    }
}