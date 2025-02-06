package com.goesbruno.movieapp.core.domain.model

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import androidx.paging.PagingState

class PagingSourceMoviesFactory {

    fun create(movies: List<Movie>) = object : PagingSource<Int, Movie>(){
        override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = 1


        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
            return LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = null
            )
        }
    }

}