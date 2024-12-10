package com.goesbruno.movieapp.popular_movie_feature.data.mapper

import com.goesbruno.movieapp.core.data.remote.model.MovieResult
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.util.toPosterUrl

fun List<MovieResult>.toMovies() = map { movieResult ->
    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAverage = movieResult.voteAverage,
        imageUrl = movieResult.posterPath?.toPosterUrl() ?: ""
    )
}