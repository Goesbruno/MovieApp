package com.goesbruno.movieapp.movie_search_feature.presentation

import androidx.paging.PagingData
import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.MovieSearchFactory
import com.goesbruno.movieapp.movie_search_feature.domain.usecases.GetMovieSearchUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieSearchViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getMovieSearchUseCase: GetMovieSearchUseCase

    lateinit var viewModel: MovieSearchViewModel

    private val fakePagingDataSearchMovies = PagingData.from(
        listOf(
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.Avengers),
            MovieSearchFactory().create(poster = MovieSearchFactory.Poster.JusticeLeague)
        )
    )

    @Before
    fun setUp() {
        viewModel = MovieSearchViewModel(getMovieSearchUseCase)
    }

    @Test
    fun `must validate paging data object values when calling movie search paging data`() = runTest {
        //Given
        whenever(getMovieSearchUseCase.invoke(any())).thenReturn(
            flowOf(fakePagingDataSearchMovies)
        )

        //When
        viewModel.fetch("")
        val result = viewModel.uiState.value.movies.first()

        //Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to the use case returns an exception`() =
        runTest {
            //Given
            whenever(getMovieSearchUseCase.invoke(any())).thenThrow(
                RuntimeException()
            )

            //When
            viewModel.fetch()

            //Then - nothing
        }

}