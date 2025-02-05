package com.goesbruno.movieapp.core.paging

import androidx.paging.PagingSource
import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.domain.model.MovieFactory
import com.goesbruno.movieapp.core.domain.model.MoviePagingFactory
import com.goesbruno.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SimilarMoviePagingSourceTest {

    @get:Rule
    private val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MovieDetailRemoteDataSource

    private val movieFactory = MovieFactory()

    private val moviePagingFactory = MoviePagingFactory().create()

    private val similarMoviePagingSource by lazy {
        SimilarMoviePagingSource(
            remoteDataSource = remoteDataSource,
            movieId = 1
        )
    }

    @Test
    fun `should return a success load result when load is called`() = runTest {
        //Given
        whenever(remoteDataSource.getSimilarMovies(any(), any()))
            .thenReturn(moviePagingFactory)

        //When
        val result = similarMoviePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val expectedResult = listOf(
            movieFactory.create(MovieFactory.Poster.Avengers),
            movieFactory.create(MovieFactory.Poster.JusticeLeague)
        )

        //Then
        assertThat(result).isEqualTo(PagingSource.LoadResult.Page(
            data = expectedResult,
            prevKey = null,
            nextKey = null
        ))

    }

    @Test
    fun`should return a error load result when load is called`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(remoteDataSource.getSimilarMovies(any(), any())).thenThrow(exception)


        //When
        val result = similarMoviePagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        //Then
        assertThat(result).isEqualTo(PagingSource.LoadResult.Error<Int, Movie>(exception))
    }

}