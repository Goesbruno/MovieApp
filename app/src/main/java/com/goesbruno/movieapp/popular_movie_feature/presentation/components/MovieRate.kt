package com.goesbruno.movieapp.popular_movie_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goesbruno.movieapp.ui.theme.yellow

@Composable
fun MovieRate(
    rate: Double,
    modifier: Modifier
) {

    Row(
        modifier = modifier
            .widthIn(max = 60.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            modifier = Modifier
                .size(12.dp),
            tint = yellow
        )
        Text(
            text = rate.toString(),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            fontSize = 10.sp
        )
    }

}


@Preview
@Composable
private fun MovieRatePreview() {
    MovieRate(
        rate = 3.5,
        modifier = Modifier
    )
}