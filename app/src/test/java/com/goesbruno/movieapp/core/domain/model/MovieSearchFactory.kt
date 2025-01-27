package com.goesbruno.movieapp.core.domain.model

class MovieSearchFactory {

    fun create(poster: Poster) = when(poster) {
        Poster.Avengers -> {
            MovieSearch(
                id = 1,
                voteAverage = 7.1,
                imageUrl = "Url"
            )
        }
        Poster.JusticeLeague -> {
            MovieSearch(
                id = 2,
                voteAverage = 5.3,
                imageUrl = "Url"
            )
        }
    }

    sealed class Poster {
        object Avengers: Poster()
        object JusticeLeague: Poster()
    }
}