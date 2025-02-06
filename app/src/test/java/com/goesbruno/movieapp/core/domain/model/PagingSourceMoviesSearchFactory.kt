package com.goesbruno.movieapp.core.domain.model

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState

class PagingSourceMoviesSearchFactory {

    fun create(movies: List<MovieSearch>) = object : PagingSource<Int, MovieSearch>(){
        override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? = 1


        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> {
            return LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = null
            )
        }
    }

}