package com.goesbruno.movieapp.popular_movie_feature

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.core.util.UtilFunctions
import com.goesbruno.movieapp.popular_movie_feature.presentation.components.MovieContent
import com.goesbruno.movieapp.popular_movie_feature.presentation.state.PopularMovieUiState
import com.goesbruno.movieapp.ui.theme.black
import com.goesbruno.movieapp.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(
    uiState: PopularMovieUiState = PopularMovieUiState(),
    navigateToMovieDetail: (Int) -> Unit = {}
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.popular_movies),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                )
            )
        },
        content = { paddingValues ->
            MovieContent(
                pagingMovies = movies,
                paddingValues = paddingValues,
                onClick = { movieId ->
                    UtilFunctions.logInfo("MOVIE_ID", movieId.toString())
                    navigateToMovieDetail(movieId)
                }
            )
        }
    )


}


@Preview
@Composable
private fun PopularMoviesScreenPreview() {
    PopularMoviesScreen()
}