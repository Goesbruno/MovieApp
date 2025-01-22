package com.goesbruno.movieapp.movie_detail_feature.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.movie_detail_feature.presentation.components.MovieDetailContent
import com.goesbruno.movieapp.movie_detail_feature.presentation.state.MovieDetailUiState
import com.goesbruno.movieapp.ui.theme.black
import com.goesbruno.movieapp.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    id: Int?,
    uiState: MovieDetailUiState,
    getMovieDetail: (MovieDetailsEvent.GetMovieDetail) -> Unit,
    onSimilarMovieClick: (Movie) -> Unit,
    onAddFavorite:(Movie) -> Unit,
    checkedFavorite: (MovieDetailsEvent.CheckedFavorite) -> Unit
) {

    val pagingSimilarMovies = uiState.results.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        if(id != null){
            getMovieDetail(MovieDetailsEvent.GetMovieDetail(id))
            checkedFavorite(MovieDetailsEvent.CheckedFavorite(id))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.detail_movie),
                        color = white
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)

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