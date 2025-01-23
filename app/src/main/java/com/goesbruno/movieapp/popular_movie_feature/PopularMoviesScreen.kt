package com.goesbruno.movieapp.popular_movie_feature

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.core.presentation.components.common.MovieAppBar
import com.goesbruno.movieapp.core.util.UtilFunctions
import com.goesbruno.movieapp.popular_movie_feature.presentation.components.MovieContent
import com.goesbruno.movieapp.popular_movie_feature.presentation.state.PopularMovieUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(
    uiState: PopularMovieUiState = PopularMovieUiState(),
    navigateToMovieDetail: (Int) -> Unit = {}
) {
    val movies = uiState.movies.collectAsLazyPagingItems()

    Scaffold (
        topBar = {

            MovieAppBar(
                title = R.string.popular_movies,
                modifier = Modifier
                    .fillMaxWidth()
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