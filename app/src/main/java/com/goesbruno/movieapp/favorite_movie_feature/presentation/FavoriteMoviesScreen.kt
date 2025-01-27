package com.goesbruno.movieapp.favorite_movie_feature.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.presentation.components.common.MovieAppBar
import com.goesbruno.movieapp.favorite_movie_feature.presentation.components.FavoriteMoviesContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesScreen(
    movies: List<Movie>,
    navigateToMovieDetail: (Int) -> Unit = {}
) {


    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.favorite_movies,
                modifier = Modifier
                    .fillMaxWidth()
            )

        },
        content = {paddingValues ->
            FavoriteMoviesContent(
                paddingValues = paddingValues,
                movies = movies,
                onClick = { movieId ->
                    navigateToMovieDetail(movieId)
                }
            )
        }
    ) 

}


@Preview
@Composable
private fun FavoriteMoviesScreenPreview() {
    FavoriteMoviesScreen(
        movies   = listOf(
                Movie(
                    id = 1,
                    title = "Homem Aranha",
                    voteAverage = 7.89,
                    imageUrl = ""
                ),
                Movie(
                    id = 2,
                    title = "Homem de Ferro",
                    voteAverage = 7.89,
                    imageUrl = ""
                ),
            )
        )

}