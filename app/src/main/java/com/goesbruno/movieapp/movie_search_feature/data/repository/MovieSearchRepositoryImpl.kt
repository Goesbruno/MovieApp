package com.goesbruno.movieapp.movie_search_feature.data.repository

import androidx.paging.PagingSource
import com.goesbruno.movieapp.core.domain.model.MovieSearch
import com.goesbruno.movieapp.core.paging.MovieSearchPagingSource
import com.goesbruno.movieapp.movie_search_feature.domain.repository.MovieSearchRepository
import com.goesbruno.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor (
    private val remoteDataSource: MovieSearchRemoteDataSource
): MovieSearchRepository {
    override fun getSearchedMovies(
        query: String
    ): PagingSource<Int, MovieSearch> {
        return MovieSearchPagingSource(query, remoteDataSource)
    }
}