package com.goesbruno.movieapp.popular_movie_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.goesbruno.movieapp.core.presentation.components.common.AsyncImageUrl
import com.goesbruno.movieapp.ui.theme.black

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    voteAverage: Double,
    imageUrl: String,
    id: Int,
    onClick: (id: Int) -> Unit = {}
) {

    Box(
        modifier = modifier
    ){
        MovieRate(
            rate = voteAverage,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .zIndex(2f)
                .padding(start = 6.dp, bottom = 8.dp)
                .background(black)
        )

        Card(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
                .padding(4.dp)
                .clickable {
                    onClick(id)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {

            Box {

                AsyncImageUrl(
                    imageUrl = imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(black)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

        }
    }


}


@Preview
@Composable
private fun MovieItemPreview() {
    MovieItem(
        voteAverage = 5.6,
        imageUrl = "",
        id = 1
    )
}