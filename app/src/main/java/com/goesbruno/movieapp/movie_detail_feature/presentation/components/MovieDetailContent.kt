package com.goesbruno.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.domain.model.MovieDetails
import com.goesbruno.movieapp.movie_detail_feature.data.mapper.toMovie
import com.goesbruno.movieapp.ui.theme.black
import com.goesbruno.movieapp.ui.theme.white
import com.goesbruno.movieapp.ui.theme.yellow
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieDetailContent(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?,
    pagingSimilarMovies: LazyPagingItems<Movie>,
    isLoading: Boolean,
    isError: String,
    iconColor: Color,
    onAddFavorite: (Movie) -> Unit = {},
    onMovieSimilarMovieClick: (Movie) -> Unit = {}
) {

    Box(
        modifier
            .fillMaxSize()
            .background(black)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MovieDetailBackdropImage(
                backdropImageUrl = movieDetails?.backdropPathUrl.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        movieDetails?.toMovie()?.let { movie ->
                            onAddFavorite(movie)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = iconColor
                    )
                }
            }
            Text(
                text = movieDetails?.title.toString(),
                color = white,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(Modifier.height(8.dp))

            FlowRow(
                mainAxisSpacing = 10.dp,
                mainAxisAlignment = MainAxisAlignment.Center,
                crossAxisSpacing = 10.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                movieDetails?.genres?.forEach { genre ->
                    GenreTag(genre = genre)
                }
            }
            Spacer(Modifier.height(8.dp))

            MovieInfoContent(movieDetails = movieDetails, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))

            RatingBar(
                rating = (movieDetails?.voteAverage?.toFloat()?.div(2f)) ?: 0f,
                modifier = Modifier.height(15.dp)
            )
            Spacer(Modifier.height(15.dp))

            MovieDetailOverview(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                overview = movieDetails?.overview.toString()
            )
            Spacer(Modifier.height(15.dp))


        }
        if (isError.isNotEmpty()) {
            Text(
                text = isError,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.TopCenter)
                    .padding(horizontal = 8.dp)
            )
        }

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                color = yellow
            )
        }
        Column(
            Modifier.align(Alignment.BottomEnd),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(id = R.string.movies_similar),
                color = white,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            Spacer(Modifier.height(8.dp))

            MovieDetailsSimilarMovies(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f),
                pagingSimilarMovies = pagingSimilarMovies,
                onMovieClick = { movie ->
                    onMovieSimilarMovieClick(movie)
                }
            )
        }
    }

}

@Preview
@Composable
fun MovieDetailsContentPreview() {
    MovieDetailContent(
        movieDetails = MovieDetails(
            id = 1,
            title = "Homem Aranha",
            genres = listOf("Ação", "Aventura", "Aventura", "Aventura", "Aventura", "Aventura"),
            overview = "Depois do nosso herói ter sido desmascarado pelo seu inimigo Mysterio, Peter Parker já não é capaz de separar a sua vida normal do seu estatuto de super-herói, pelo que só lhe resta pedir ajuda ao Mestre das Artes Místicas, o Doutor Estranho, para que apague a sua real identidade da cabeça de todos. No entanto, esse feitiço leva-o a uma realidade que não é a sua e onde terá de enfrentar novos inimigos, ainda mais perigosos, forçando-o a descobrir o que realmente significa ser o Homem-Aranha.",
            backdropPathUrl = "",
            releaseDate = "25/05/2022",
            voteAverage = 6.0
        ),
        pagingSimilarMovies = flowOf(PagingData.from(emptyList<Movie>())).collectAsLazyPagingItems(),
        isError = "Error",
        isLoading = false,
        iconColor = Color.Red,
    )
}