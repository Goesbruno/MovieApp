package com.goesbruno.movieapp.movie_detail_feature.presentation.components

import com.goesbruno.movieapp.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goesbruno.movieapp.core.domain.model.MovieDetails


@Composable
fun MovieInfoContent(
    modifier: Modifier = Modifier,
    movieDetails: MovieDetails?
    ) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MovieDetailInfo(
            name = stringResource(id = R.string.vote_average),
            value = movieDetails?.voteAverage.toString()
        )

        MovieDetailInfo(
            name = stringResource(id = R.string.duration),
            value = stringResource(id = R.string.duration_minutes,
                movieDetails?.duration.toString())
        )
        MovieDetailInfo(
            name = stringResource(id = R.string.release_date),
            value = movieDetails?.releaseDate.toString()
        )

    }
}

@Composable
fun MovieDetailInfo(
    name: String,
    value: String
) {
    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineMedium.copy(fontSize = 13.sp, letterSpacing = 1.sp),
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp)
        )
    }
}


@Preview
@Composable
private fun MovieDetailInfoPreview() {
    MovieDetailInfo(
        name = "Título",
        value = "Lorem ipsum dolor"
    )
}


@Preview(showBackground = true)
@Composable
private fun MovieInfoContentPreview() {
    MovieInfoContent(
        movieDetails = MovieDetails(
            id = 1,
            title = "Filme",
            genres = listOf("Aventura", "Drama", "Suspense"),
            overview = null,
            backdropPathUrl = null,
            releaseDate = "2024",
            voteAverage = 7.5,
            duration = 120,
            voteCount = 100
        ),
        modifier = Modifier.fillMaxWidth()
    )
}