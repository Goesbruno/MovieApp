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
class IsFavoriteMovieUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repository: FavoriteMovieRepository

    private val movie = MovieFactory().create(MovieFactory.Poster.Avengers)

    private val isFavoriteMoviesUseCase by lazy {
        IsFavoriteMovieUseCaseImpl(repository)
    }

    @Test
    fun `should return a True Success ResultData when the repository returns a Boolean successfully`() = runTest {
        //Given
        whenever(repository.isFavorite(movie.id)).thenReturn(true)

        //When
        val result = isFavoriteMoviesUseCase.invoke(
            params = IsFavoriteMovieUseCase.Params(
                movieId = movie.id
            )
        ).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Success(true))

    }

    @Test
    fun `should return a False Success ResultData when the repository returns a Boolean successfully`() = runTest {
        //Given
        whenever(repository.isFavorite(movie.id)).thenReturn(false)

        //When
        val result = isFavoriteMoviesUseCase.invoke(
            params = IsFavoriteMovieUseCase.Params(
                movieId = movie.id
            )
        ).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Success(false))

    }


    @Test
    fun `should return a Failure ResultData when the repository returns a exception`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(repository.isFavorite(movie.id)).thenThrow(exception)

        //When
        val result = isFavoriteMoviesUseCase.invoke(
            params = IsFavoriteMovieUseCase.Params(
                movieId = movie.id
            )
        ).first()

        //Then
        assertThat(result).isEqualTo(ResultData.Failure(exception))

    }

}