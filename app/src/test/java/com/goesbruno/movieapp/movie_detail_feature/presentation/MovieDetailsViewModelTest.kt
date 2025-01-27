package com.goesbruno.movieapp.movie_detail_feature.presentation

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.paging.PagingData
import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.MovieDetailsFactory
import com.goesbruno.movieapp.core.domain.model.MovieFactory
import com.goesbruno.movieapp.core.util.ResultData
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.AddFavoriteMovieUseCase
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.DeleteFavoriteMovieUseCase
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.IsFavoriteMovieUseCase
import com.goesbruno.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsViewModelTest {
    @get:Rule
    val dispatcherRule = TestDispatcherRule()
    @Mock
    lateinit var getMovieDetailsUseCase: GetMovieDetailsUseCase
    @Mock
    lateinit var addFavoriteMovieUseCase: AddFavoriteMovieUseCase
    @Mock
    lateinit var deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase
    @Mock
    lateinit var isFavoriteMovieUseCase: IsFavoriteMovieUseCase
    @Mock
    lateinit var savedStateHandle: SavedStateHandle
    private val movieDetailsFactory = MovieDetailsFactory().create(poster = MovieDetailsFactory.Poster.Avengers)
    private val fakePagingData = PagingData.from(listOf(
            MovieFactory().create(poster = MovieFactory.Poster.Avengers),
            MovieFactory().create(poster = MovieFactory.Poster.JusticeLeague)
        ))
    private val movie = MovieFactory().create(poster = MovieFactory.Poster.Avengers)
    private val viewModel by lazy {
        MovieDetailsViewModel(
            getMovieDetailsUseCase = getMovieDetailsUseCase,
            addFavoriteMovieUseCase = addFavoriteMovieUseCase,
            deleteFavoriteMovieUseCase = deleteFavoriteMovieUseCase,
            isFavoriteMovieUseCase = isFavoriteMovieUseCase,
            savedStateHandle = savedStateHandle.apply {
                whenever(savedStateHandle.get<Int>("movieId")).thenReturn(movie.id)
            }
        )
    }


    @Test
    fun `should notify uiState with success when getMovieDetails returns success`() = runTest {
        //Given
        whenever(getMovieDetailsUseCase.invoke(any())).thenReturn(
            flowOf(
                ResultData.Success(flowOf(fakePagingData) to movieDetailsFactory)
            )
        )
        val argumentCaptor = argumentCaptor<GetMovieDetailsUseCase.Params>()

        //When
        viewModel.uiState.value.isLoading

        //Then
        verify(getMovieDetailsUseCase).invoke(argumentCaptor.capture())
        assertThat(movieDetailsFactory.id).isEqualTo(argumentCaptor.firstValue.movieId)
        val movieDetails = viewModel.uiState.value.movieDetails
        val results = viewModel.uiState.value.results
        assertThat(movieDetails).isNotNull()
        assertThat(results).isNotNull()
    }

    @Test
    fun `should notify uiState with Failure when getMoviesDetail returns a exception`() = runTest {
        //Given
        val exception = Exception("A error occurred")
        whenever(getMovieDetailsUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Failure(exception))
        )

        //When
        viewModel.uiState.value.isLoading

        //Then
        val error = viewModel.uiState.value.error
        assertThat(exception.message).isEqualTo(error)

    }

    @Test
    fun `Should call removeFavorite and notify the uiState with a unfilled favorite icon if the movie is favorite`() =
        runTest {
            //Given
            whenever(deleteFavoriteMovieUseCase.invoke(any())).thenReturn(
                flowOf(ResultData.Success(Unit))
            )
            whenever(isFavoriteMovieUseCase.invoke(any())).thenReturn(
                flowOf(ResultData.Success(true))
            )
            val deleteArgumentCaptor = argumentCaptor<DeleteFavoriteMovieUseCase.Params>()
            val checkedArgumentCaptor = argumentCaptor<IsFavoriteMovieUseCase.Params>()

            //When
            viewModel.onAddFavorite(movie = movie)

            //Then
            verify(deleteFavoriteMovieUseCase).invoke(deleteArgumentCaptor.capture())
            assertThat(movie).isEqualTo(deleteArgumentCaptor.firstValue.movie)

            verify(isFavoriteMovieUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.value.iconColor
            assertThat(Color.White).isEqualTo(iconColor)
        }

    @Test
    fun `Should notify the uiState with a red filled icon when the current icon is unchecked`() =
        runTest {
            //Given
            whenever(addFavoriteMovieUseCase.invoke(any())).thenReturn(
                flowOf(ResultData.Success(Unit))
            )
            whenever(isFavoriteMovieUseCase.invoke(any())).thenReturn(
                flowOf(ResultData.Success(false))
            )
            val addArgumentCaptor = argumentCaptor<AddFavoriteMovieUseCase.Params>()
            val checkedArgumentCaptor = argumentCaptor<IsFavoriteMovieUseCase.Params>()

            //When
            viewModel.onAddFavorite(movie = movie)

            //Then
            verify(addFavoriteMovieUseCase).invoke(addArgumentCaptor.capture())
            assertThat(movie).isEqualTo(addArgumentCaptor.firstValue.movie)

            verify(isFavoriteMovieUseCase).invoke(checkedArgumentCaptor.capture())
            assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)

            val iconColor = viewModel.uiState.value.iconColor
            assertThat(Color.Red).isEqualTo(iconColor)
        }

    @Test
    fun `should notify uiState with the favorite icon filled in if favorite icon check return true `() = runTest {
        //Given
        whenever(isFavoriteMovieUseCase.invoke(any())).thenReturn(
            flowOf(ResultData.Success(true))
        )
        val checkedArgumentCaptor = argumentCaptor<IsFavoriteMovieUseCase.Params>()

        //When
        viewModel.uiState.value.isLoading

        //Then
        verify(isFavoriteMovieUseCase).invoke(checkedArgumentCaptor.capture())
        assertThat(movie.id).isEqualTo(checkedArgumentCaptor.firstValue.movieId)
        val iconColor = viewModel.uiState.value.iconColor
        assertThat(Color.Red).isEqualTo(iconColor)
    }
}