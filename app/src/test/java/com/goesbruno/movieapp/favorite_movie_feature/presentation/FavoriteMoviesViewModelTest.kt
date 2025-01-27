package com.goesbruno.movieapp.favorite_movie_feature.presentation

import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.domain.model.MovieFactory
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.GetFavoriteMoviesUseCase
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
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteMoviesViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase

    private val viewModel by lazy {
        FavoriteMoviesViewModel(getFavoriteMoviesUseCase)
    }

    private val fakeFavoriteMovies: List<Movie> = listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JusticeLeague)
        )


    @Test
    fun `must validate the data object values when calling the list os favorites`() = runTest {
        //Given
        whenever(getFavoriteMoviesUseCase.invoke()).thenReturn(
            flowOf(fakeFavoriteMovies)
        )

        //When
        val result = viewModel.uiState.value.movies.first()

        //Then
        assertThat(result).isNotEmpty()
        assertThat(result).contains(fakeFavoriteMovies[0])
    }


}