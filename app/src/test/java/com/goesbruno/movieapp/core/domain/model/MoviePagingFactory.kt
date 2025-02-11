package com.goesbruno.movieapp.core.domain.model

class MoviePagingFactory {

    fun create() = MoviePaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            Movie(
                id = 1,
                title = "Avengers",
                voteAverage = 7.1,
                imageUrl = "Url"
            ),
            Movie(
                id = 2,
                title = "Justice League",
                voteAverage = 5.3,
                imageUrl = "Url"
            )
        )
    )
}