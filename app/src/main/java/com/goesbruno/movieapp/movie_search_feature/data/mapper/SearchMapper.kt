package com.goesbruno.movieapp.movie_search_feature.data.mapper

import com.goesbruno.movieapp.core.data.remote.model.SearchResult
import com.goesbruno.movieapp.core.domain.model.MovieSearch
import com.goesbruno.movieapp.core.util.toPosterUrl

fun List<SearchResult>.toMovieSearch() = map {searchResult ->
    MovieSearch(
        id = searchResult.id,
        imageUrl = searchResult.posterPath.toPosterUrl(),
        voteAverage = searchResult.voteAverage
    )
}