package com.goesbruno.movieapp.movie_detail_feature.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.presentation.components.common.MovieAppBar
import com.goesbruno.movieapp.movie_detail_feature.presentation.components.MovieDetailContent
import com.goesbruno.movieapp.movie_detail_feature.presentation.state.MovieDetailUiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    uiState: MovieDetailUiState,
    onSimilarMovieClick: (Movie) -> Unit,
    onAddFavorite:(Movie) -> Unit,
) {

    val pagingSimilarMovies = uiState.results.collectAsLazyPagingItems()


    Scaffold(
        topBar = {
            MovieAppBar(
                title = R.string.detail_movie,
                modifier = Modifier
                    .fillMaxWidth()
            )

        },
        content = {
            MovieDetailContent(
                movieDetails = uiState.movieDetails,
                pagingSimilarMovies = pagingSimilarMovies,
                isLoading = uiState.isLoading,
                isError = uiState.error,
                iconColor = uiState.iconColor,
                onAddFavorite = { movie ->
                    onAddFavorite(movie)
                },
                onMovieSimilarMovieClick = { movie ->
                    onSimilarMovieClick(movie)
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    )
}