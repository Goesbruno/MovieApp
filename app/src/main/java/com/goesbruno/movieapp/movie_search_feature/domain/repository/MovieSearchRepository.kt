package com.goesbruno.movieapp.movie_search_feature.domain.repository

import androidx.paging.PagingSource
import com.goesbruno.movieapp.core.domain.model.MovieSearch

interface MovieSearchRepository {
    fun getSearchedMovies(query: String): PagingSource<Int, MovieSearch>
}