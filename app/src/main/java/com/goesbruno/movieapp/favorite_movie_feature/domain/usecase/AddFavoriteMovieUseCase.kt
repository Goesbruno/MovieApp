package com.goesbruno.movieapp.favorite_movie_feature.domain.usecase

import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.util.ResultData
import com.goesbruno.movieapp.favorite_movie_feature.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface AddFavoriteMovieUseCase {

    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>>
    data class Params(val movie: Movie)
}


class AddFavoriteMovieUseCaseImpl @Inject constructor(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : AddFavoriteMovieUseCase {

    override suspend fun invoke(params: AddFavoriteMovieUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            try {
                val insert = favoriteMovieRepository.insertMovie(params.movie)
                emit(ResultData.Success(insert))
            } catch (e: Exception) {
                emit(ResultData.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }


}