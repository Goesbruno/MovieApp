package com.goesbruno.movieapp.core.domain.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum

class MovieDetailsFactory {

    fun create(poster: Poster) = when(poster) {
        Poster.Avengers -> {
            MovieDetails(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                genres = listOf("Aventura, Ação, Ficção Científica"),
                overview = LoremIpsum(100).values.first(),
                backdropPathUrl = "Url",
                releaseDate = "04/05/06",
                duration = 126,
                voteCount = 7
            )
        }
        Poster.JusticeLeague -> {
            MovieDetails(
                id = 2,
                title = "Justice League",
                voteAverage = 5.3,
                genres = listOf("Aventura, Ação, Ficção Científica"),
                overview = LoremIpsum(100).values.first(),
                backdropPathUrl = "Url",
                releaseDate = "04/05/06",
                duration = 126,
                voteCount = 7
            )
        }
    }

    sealed class Poster {
        object Avengers: Poster()
        object JusticeLeague: Poster()
    }
}