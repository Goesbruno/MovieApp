package com.goesbruno.movieapp.core.paging

import androidx.paging.PagingSource
import com.goesbruno.movieapp.TestDispatcherRule
import com.goesbruno.movieapp.core.domain.model.MovieSearch
import com.goesbruno.movieapp.core.domain.model.MovieSearchFactory
import com.goesbruno.movieapp.core.domain.model.MovieSearchPagingFactory
import com.goesbruno.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
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
class MovieSearchPagingSourceTest {

    @get:Rule
    private val dispatcherRule = TestDispatcherRule()

    @Mock
    lateinit var remoteDataSource: MovieSearchRemoteDataSource

    private val movieSearchFactory = MovieSearchFactory()

    private val movieSearchPagingFactory = MovieSearchPagingFactory().create()

    private val movieSearchPagingSource by lazy {
        MovieSearchPagingSource(
            query = "",
            remoteDataSource = remoteDataSource
        )
    }

    @Test
    fun `should return a success load result when load is called`() = runTest {
        //Given
        whenever(remoteDataSource.getSearchedMovies(any(), any()))
            .thenReturn(movieSearchPagingFactory)

        //When
        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        val expectedResult = listOf(
            movieSearchFactory.create(MovieSearchFactory.Poster.Avengers),
            movieSearchFactory.create(MovieSearchFactory.Poster.JusticeLeague)
        )

        //Then
        assertThat(result).isEqualTo(
            PagingSource.LoadResult.Page(
                data = expectedResult,
                prevKey = null,
                nextKey = null
            )
        )


    }

    @Test
    fun`should return a error load result when load is called`() = runTest {
        //Given
        val exception = RuntimeException()
        whenever(remoteDataSource.getSearchedMovies(any(), any()))
            .thenThrow(exception)

        //When
        val result = movieSearchPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        //Then
        assertThat(result).isEqualTo(
            PagingSource.LoadResult.Error<Int, MovieSearch>(
                throwable = exception
            )
        )

    }

}