package com.goesbruno.movieapp.movie_search_feature.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.core.presentation.components.common.MovieAppBar
import com.goesbruno.movieapp.movie_search_feature.presentation.components.SearchContent
import com.goesbruno.movieapp.movie_search_feature.presentation.state.MovieSearchUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchScreen(
    uiState: MovieSearchUiState = MovieSearchUiState(),
    onEvent: (MovieSearchEvent) -> Unit = {},
    onFetch: (String) -> Unit = {},
    navigateToMovieDetail: (Int) -> Unit = {}
) {
    val pagingMovies = uiState.movies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.search_movies,
                modifier = Modifier
                    .fillMaxWidth()
            )

        },
        content = { innerPadding ->
            Spacer(Modifier.height(60.dp))
            SearchContent(
                paddingValues = innerPadding,
                pagingMovies = pagingMovies,
                query = uiState.query,
                onSearch = {
                    onFetch(it)
                },
                onEvent = {
                    onEvent(it)
                },
                onDetail = { movieId ->
                    navigateToMovieDetail(movieId)
                }
            )
        }
    )


}

@Preview
@Composable
private fun MovieSearchScreenPreview() {
    MovieSearchScreen()
}