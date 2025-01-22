package com.goesbruno.movieapp.favorite_movie_feature.data.mapper

import com.goesbruno.movieapp.core.data.local.entity.MovieEntity
import com.goesbruno.movieapp.core.domain.model.Movie

fun List<MovieEntity>.toMovies() = map { movieEntity ->
    Movie(
        id = movieEntity.movieId,
        title = movieEntity.title,
        imageUrl = movieEntity.imageUrl
    )
}

fun Movie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        movieId = id,
        title = title,
        imageUrl = imageUrl
    )
}