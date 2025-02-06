package com.goesbruno.movieapp.favorite_movie_feature.domain.usecase

import com.goesbruno.movieapp.core.util.ResultData
import com.goesbruno.movieapp.favorite_movie_feature.domain.repository.FavoriteMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface IsFavoriteMovieUseCase {

    suspend operator fun invoke(params: Params): Flow<ResultData<Boolean>>
    data class Params(val movieId: Int)
}


class  IsFavoriteMovieUseCaseImpl @Inject constructor(
    private val favoriteMovieRepository: FavoriteMovieRepository
): IsFavoriteMovieUseCase {

    override suspend fun invoke(params: IsFavoriteMovieUseCase.Params): Flow<ResultData<Boolean>> {
        return flow {
            try {
                val isFavorite = favoriteMovieRepository.isFavorite(params.movieId)
                emit(ResultData.Success(isFavorite))
            } catch (e: Exception){
                emit(ResultData.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
    }


}