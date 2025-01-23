package com.goesbruno.movieapp.core.presentation.components.common

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.placeholder
import com.goesbruno.movieapp.R

@Composable
fun AsyncImageUrl(
    modifier: Modifier = Modifier,
    imageUrl: String,
    crossFadeEnable: Boolean = true,
    @DrawableRes errorImage: Int = R.drawable.ic_error_image,
    @DrawableRes placeholderImage: Int = R.drawable.ic_placeholder,
    contentScale: ContentScale = ContentScale.FillHeight,
    contentDescription: String = ""
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(crossFadeEnable)
            .error(errorImage)
            .placeholder(placeholderImage)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}