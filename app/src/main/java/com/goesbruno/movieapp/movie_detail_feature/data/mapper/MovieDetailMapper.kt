package com.goesbruno.movieapp.movie_detail_feature.data.mapper

import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.domain.model.MovieDetails

fun MovieDetails.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        imageUrl = backdropPathUrl.toString(),
        voteAverage = voteAverage
    )
}