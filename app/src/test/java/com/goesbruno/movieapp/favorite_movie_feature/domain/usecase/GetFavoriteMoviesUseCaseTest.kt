package com.goesbruno.movieapp.favorite_movie_feature.domain.usecase

import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.MovieFactory
import com.goesbruno.movieapp.favorite_movie_feature.domain.repository.FavoriteMovieRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetFavoriteMoviesUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repository: FavoriteMovieRepository

    private val movies = listOf(
        MovieFactory().create(MovieFactory.Poster.Avengers),
        MovieFactory().create(MovieFactory.Poster.JusticeLeague)
    )

    private val getFavoriteMoviesUseCase by lazy {
        GetFavoriteMoviesUseCaseImpl(repository)
    }

    @Test
    fun`should return a list o movies when repository provides the list successfully`() = runTest {
        //Given
        whenever(repository.getMovies()).thenReturn(
            flowOf(movies)
        )

        //When
        val result = getFavoriteMoviesUseCase.invoke().first()

        //Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(movies[1])
    }

    @Test
    fun`should return a empty flow when repository fails to provide the list of movies`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(repository.getMovies()).thenThrow(exception)

        //When
        val result = getFavoriteMoviesUseCase.invoke().toList()

        //Then
        verify(repository).getMovies()
        assertThat(result).isEmpty()

    }

}