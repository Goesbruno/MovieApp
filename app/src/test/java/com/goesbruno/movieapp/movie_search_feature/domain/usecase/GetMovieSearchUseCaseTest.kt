package com.goesbruno.movieapp.movie_search_feature.domain.usecase

import androidx.paging.PagingConfig
import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.MovieSearchFactory
import com.goesbruno.movieapp.core.domain.model.PagingSourceMoviesSearchFactory
import com.goesbruno.movieapp.movie_search_feature.domain.repository.MovieSearchRepository
import com.goesbruno.movieapp.movie_search_feature.domain.usecases.GetMovieSearchUseCase
import com.goesbruno.movieapp.movie_search_feature.domain.usecases.GetMovieSearchUseCaseImpl
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
class GetMovieSearchUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repository: MovieSearchRepository

    private val movies = listOf(
        MovieSearchFactory().create(MovieSearchFactory.Poster.Avengers),
        MovieSearchFactory().create(MovieSearchFactory.Poster.JusticeLeague)
    )

    private val fakePagingSource = PagingSourceMoviesSearchFactory().create(movies)

    private val getMovieSearchUseCase by lazy {
        GetMovieSearchUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() = runTest {
        //Given
        whenever(repository.getSearchedMovies(query = ""))
            .thenReturn(fakePagingSource)

        //When
        val result = getMovieSearchUseCase.invoke(
            params = GetMovieSearchUseCase.Params(
                query = "",
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).first()

        //Then
        verify(repository).getSearchedMovies(query = "")
        assertThat(result).isNotNull()
    }

    @Test
    fun `should emit an empty stream when an exception is thrown while calling the invoke method`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(repository.getSearchedMovies(query = ""))
            .thenThrow(exception)

        //When
        val result = getMovieSearchUseCase.invoke(
            params = GetMovieSearchUseCase.Params(
                query = "",
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        ).toList()

        //Then
        verify(repository).getSearchedMovies(query = "")
        assertThat(result).isEmpty()
    }

}