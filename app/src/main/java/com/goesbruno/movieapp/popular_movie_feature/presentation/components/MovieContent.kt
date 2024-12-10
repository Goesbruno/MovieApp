package com.goesbruno.movieapp.popular_movie_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.goesbruno.movieapp.ui.theme.black

@Composable
fun MovieContent(
    modifier: Modifier = Modifier,
    pagingMovies: LazyPagingItems<Movie>,
    paddingValues: PaddingValues,
    onClick: (movieId: Int) -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(black)
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalArrangement = Arrangement.Center
        ) {

            items(pagingMovies.itemCount) { index ->
                val movie = pagingMovies[index]

                movie?.let { movie ->
                    MovieItem(
                        voteAverage = movie.voteAverage,
                        imageUrl = movie.imageUrl,
                        id = movie.id,
                        onClick = { movieId ->
                            onClick(movieId)
                        }
                    )
                }

            }
            pagingMovies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            LoadingView()
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            ErrorScreen(
                                message = "Verifique sua conexão com a internet",
                                retry = {
                                    retry()
                                }
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item(
                            span = {
                                GridItemSpan(maxLineSpan)
                            }
                        ) {
                            ErrorScreen(
                                message = "Verifique sua conexão com a internet",
                                retry = {
                                    retry()
                                }
                            )
                        }
                    }

                }
            }
        }

    }

}

//@Preview
//@Composable
//private fun MovieContentPreview() {
//    MovieContent(
//
//    )
//}