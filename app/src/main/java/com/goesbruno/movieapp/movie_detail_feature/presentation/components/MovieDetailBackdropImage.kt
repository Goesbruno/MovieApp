package com.goesbruno.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.goesbruno.movieapp.R
import com.goesbruno.movieapp.core.presentation.components.common.AsyncImageUrl

@Composable
fun MovieDetailBackdropImage(
    backdropImageUrl: String,
    modifier: Modifier
) {

    Box(modifier = modifier){

        AsyncImageUrl(
            imageUrl = backdropImageUrl,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

    }

}

@Preview
@Composable
private fun MovieDetailBackdropImagePreview() {
    MovieDetailBackdropImage(
        backdropImageUrl = "",
        modifier = Modifier.fillMaxWidth()
            .height(200.dp)
    )
}