package com.goesbruno.movieapp.popular_movie_feature.domain.usecase

import androidx.paging.PagingConfig
import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.MovieFactory
import com.goesbruno.movieapp.core.domain.model.PagingSourceMoviesFactory
import com.goesbruno.movieapp.popular_movie_feature.domain.repository.PopularMovieRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
class GetPopularMoviesUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repository: PopularMovieRepository

    private val movies = listOf(
        MovieFactory().create(MovieFactory.Poster.Avengers),
        MovieFactory().create(MovieFactory.Poster.JusticeLeague)
    )

    private val fakePagingSource = PagingSourceMoviesFactory().create(movies)

    private val getPopularMoviesUseCase by lazy {
        GetPopularMoviesUseCaseImpl(repository = repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        //Given
        whenever(repository.getPopularMovies())
            .thenReturn(fakePagingSource)

        //When
        val result = getPopularMoviesUseCase.invoke(
            params = GetPopularMoviesUseCase.Params(
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).first()

        //Then
        verify(repository).getPopularMovies()
        assertThat(result).isNotNull()
    }

    @Test
    fun `should emit an empty stream when an exception is thrown while calling the invoke method` () = runTest {
        //Given
        val exception = RuntimeException()
        whenever(repository.getPopularMovies()).thenThrow(exception)

        //When
        val result = getPopularMoviesUseCase.invoke(
            params = GetPopularMoviesUseCase.Params(
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        val resultList = result.toList()
        verify(repository).getPopularMovies()
        assertThat(resultList).isEmpty()
    }
}