package com.goesbruno.movieapp.favorite_movie_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.core.presentation.components.common.AsyncImageUrl
import com.goesbruno.movieapp.ui.theme.black
import com.goesbruno.movieapp.ui.theme.white

@Composable
fun FavoriteMovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (id: Int) -> Unit = {}
) {

    Card(
        modifier
            .fillMaxWidth()
            .clickable {
                onClick(movie.id)
            }
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(black),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {

                AsyncImageUrl(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    imageUrl = movie.imageUrl,
                    crossFadeEnable = true,
                    contentScale = ContentScale.FillWidth,
                )
            }
            Text(
                text = movie.title,
                maxLines = 1,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = white,
                overflow = TextOverflow.Ellipsis
            )

        }
    }

}


@Preview
@Composable
private fun FavoriteMovieItemPreview() {
    FavoriteMovieItem(
        movie = Movie(
            id = 1,
            title = "A volta dos que não foram",
            voteAverage = 7.5,
            imageUrl = ""
        )
    )
}