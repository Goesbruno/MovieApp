package com.goesbruno.movieapp.favorite_movie_feature.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.favorite_movie_feature.presentation.components.FavoriteMoviesContent
import com.goesbruno.movieapp.favorite_movie_feature.presentation.state.FavoriteMovieUiState
import com.goesbruno.movieapp.ui.theme.black
import com.goesbruno.movieapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteMoviesScreen(
    uiState: FavoriteMovieUiState,
    navigateToMovieDetail: (Int) -> Unit = {}
) {

    val movies = uiState.movies

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.favorite_movies),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                )
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
        uiState = FavoriteMovieUiState(
            movies = listOf(
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
    )
}