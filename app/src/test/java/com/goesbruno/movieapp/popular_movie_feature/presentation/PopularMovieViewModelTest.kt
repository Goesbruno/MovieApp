package com.goesbruno.movieapp.popular_movie_feature.presentation

import androidx.paging.PagingData
import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.MovieFactory
import com.goesbruno.movieapp.popular_movie_feature.domain.usecase.GetPopularMoviesUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class PopularMovieViewModelTest {

    //Troca a rule que usaria o Dispatcher Main pelo TestDispatcherRule
    @get: Rule
    val dispatcherRule = TestDispatcherRule()

    //Mock do usecase necessário para executar o viewModel
    @Mock
    lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

    private val viewModel by lazy {
        PopularMovieViewModel(getPopularMoviesUseCase)
    }

    private val fakePagingDataMovies = PagingData.from(
        listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JusticeLeague)
        )
    )


    @Test
    fun `must validate paging data object values when calling paging data from movies`() = runTest {
        //Given
        whenever(getPopularMoviesUseCase.invoke(any())).thenReturn(
            flowOf(fakePagingDataMovies)
        )

        //When
        val result = viewModel.uiState.value.movies.first()

        //Then
        assertThat(result).isNotNull()
    }

    @Test(expected = RuntimeException::class)
    fun `must throw an exception when the calling to the use case returns an exception`() =
        runTest {
            //Given
            whenever(getPopularMoviesUseCase.invoke(any())).thenThrow(
                RuntimeException()
            )

            //When
            val result = viewModel.uiState.value.movies.first()

            //Then
            assertThat(result).isNull()
        }
}