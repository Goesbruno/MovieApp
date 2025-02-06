package com.goesbruno.movieapp.movie_detail_feature.domain.usecase

import androidx.paging.PagingConfig
import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.MovieDetailsFactory
import com.goesbruno.movieapp.core.domain.model.MovieFactory
import com.goesbruno.movieapp.core.domain.model.PagingSourceMoviesFactory
import com.goesbruno.movieapp.core.util.ResultData
import com.goesbruno.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class GetMovieDetailsUseCaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var repository: MovieDetailsRepository

    private val movies = listOf(MovieFactory().create(MovieFactory.Poster.Avengers))

    private val movieDetailsFactory =
        MovieDetailsFactory().create(MovieDetailsFactory.Poster.Avengers)

    private val fakePagingSource = PagingSourceMoviesFactory().create(movies)

    private val getMovieDetailsUseCase by lazy {
        GetMovieDetailsUseCaseImpl(repository)
    }

    @Test
    fun `should return Success ResultData when both requests return success`() = runTest {
        //Given
        whenever(repository.getMovieDetails(movieId = movies[0].id))
            .thenReturn(movieDetailsFactory)
        whenever(repository.getSimilarMovies(movieId = movies[0].id))
            .thenReturn(fakePagingSource)

        //When
        val result = getMovieDetailsUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movies[0].id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(repository).getMovieDetails(movies[0].id)
        verify(repository).getSimilarMovies(movies[0].id)
        assertThat(result).isNotNull()
        assertThat(result is ResultData.Success).isTrue()

    }

    @Test
    fun `should return Error ResultData when getMovieDetails return a Exception`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(repository.getMovieDetails(movieId = movies[0].id))
            .thenThrow(exception)
        whenever(repository.getSimilarMovies(movieId = movies[0].id))
            .thenReturn(fakePagingSource)

        //When
        val result = getMovieDetailsUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movies[0].id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(repository).getMovieDetails(movies[0].id)
        verify(repository).getSimilarMovies(movies[0].id)
        assertThat(result).isEqualTo(ResultData.Failure(exception))
    }

    @Test
    fun `should return Error ResultData when getSimilarMovies return a Exception`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(repository.getSimilarMovies(movieId = movies[0].id))
            .thenThrow(exception)

        //When
        val result = getMovieDetailsUseCase.invoke(
            GetMovieDetailsUseCase.Params(
                movieId = movies[0].id,
                pagingConfig = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20
                )
            )
        )

        //Then
        verify(repository).getSimilarMovies(movies[0].id)
        assertThat(result).isEqualTo(ResultData.Failure(exception))
    }

}