package com.goesbruno.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.presentation.components.common.ErrorScreen
import com.goesbruno.movieapp.core.presentation.components.common.LoadingView
import com.goesbruno.movieapp.popular_movie_feature.presentation.components.MovieItem

@Composable
fun MovieDetailsSimilarMovies(
    modifier: Modifier = Modifier,
    pagingSimilarMovies: LazyPagingItems<Movie>,
    onMovieClick: (Movie) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {

        items(pagingSimilarMovies.itemCount){index ->
            val movie = pagingSimilarMovies[index]
            movie?.let { movie ->
                MovieItem(
                    voteAverage = movie.voteAverage,
                    imageUrl = movie.imageUrl,
                    id = movie.id,
                    onClick = {
                        onMovieClick(movie)
                    }
                )
            }
        }
        pagingSimilarMovies.apply {
            when{
                loadState.refresh is LoadState.Loading -> {
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        LoadingView()
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        LoadingView()
                    }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = pagingSimilarMovies.loadState.refresh as LoadState.Error
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        ErrorScreen(message = error.error.message.toString()) {
                            retry()
                        }
                    }
                }

                loadState.append is LoadState.Error -> {
                    val error = pagingSimilarMovies.loadState.refresh as LoadState.Error
                    item(span = {
                        GridItemSpan(maxLineSpan)
                    }) {
                        ErrorScreen(message = error.error.message.toString()) {
                            retry()
                        }
                    }
                }
            }
        }
    }
}