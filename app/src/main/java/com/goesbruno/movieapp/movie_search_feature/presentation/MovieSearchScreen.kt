package com.goesbruno.movieapp.movie_search_feature.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.movie_search_feature.presentation.components.SearchContent
import com.goesbruno.movieapp.movie_search_feature.presentation.state.MovieSearchUiState
import com.goesbruno.movieapp.ui.theme.black


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
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.search_movies),
                        color = Color.White
                    )
                },
                colors = topAppBarColors(
                    containerColor = black
                )
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