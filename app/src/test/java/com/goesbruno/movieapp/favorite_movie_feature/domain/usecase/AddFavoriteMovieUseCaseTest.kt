package com.goesbruno.movieapp.favorite_movie_feature.domain.usecase

import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.MovieFactory
import com.goesbruno.movieapp.core.util.ResultData
import com.goesbruno.movieapp.favorite_movie_feature.domain.repository.FavoriteMovieRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AddFavoriteMovieUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repository: FavoriteMovieRepository

    private val movie = MovieFactory().create(MovieFactory.Poster.Avengers)

    private val addFavoriteMovieUseCase by lazy {
        AddFavoriteMovieUseCaseImpl(repository)
    }

    @Test
    fun `should return Unit Success ResultData when a movie is added successfully`() = runTest {
        //Given
        whenever(repository.insertMovie(movie)).thenReturn(Unit)

        //When
        val result = addFavoriteMovieUseCase
            .invoke(AddFavoriteMovieUseCase.Params(movie)).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Success(Unit))
    }

    @Test
    fun `should return a exception Failure ResultData when a movie is added unsuccessfully`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(repository.insertMovie(movie)).thenThrow(exception)

        //When
        val result = addFavoriteMovieUseCase
            .invoke(
                AddFavoriteMovieUseCase.Params(movie)
            ).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Failure(exception))

    }
}