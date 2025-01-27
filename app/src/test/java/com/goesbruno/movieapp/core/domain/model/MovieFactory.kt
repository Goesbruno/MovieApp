package com.goesbruno.movieapp.core.domain.model

class MovieFactory {

    fun create(poster: Poster) = when(poster) {
        Poster.Avengers -> {
            Movie(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                imageUrl = "Url"
            )
        }
        Poster.JusticeLeague -> {
            Movie(
                id = 2,
                title = "Justice League",
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