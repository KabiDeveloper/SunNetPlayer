package com.kabiplayer.screens.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kabiplayer.R
import coil.compose.AsyncImage
import com.kabiplayer.constants.ImageSize
import com.kabiplayer.data_models.Media
import com.kabiplayer.extensions.getPosterUrl
import com.kabiplayer.helpers.getGenresText

@Composable
fun FeedHeader(data: Media, onInfoClick: (Media) -> Unit) {
    val (posterUrl, genresText) = remember(data) {
        when (data) {
            is Media.Movie -> Pair(
                data.getPosterUrl(ImageSize.ORIGINAL),
                getGenresText(data.genreIds)
            )

            is Media.Tv -> Pair(data.getPosterUrl(ImageSize.ORIGINAL), getGenresText(data.genreIds))
            else -> Pair(null, null)
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .aspectRatio(0.7f)
    ) {
        AsyncImage(
            model = posterUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorResource(R.color.blue_transparent),
                            Color.Transparent
                        )
                    )
                )
        )


    }
}

val sampleMovie = Media.Movie(
    475557,
    "Joker",
    "/mZuAPY4ETMQPHhCXIcJ90kd6RaS.jpg",
    "",
    "",
    "",
    8.2,
    listOf(80, 53, 18)
)

@Composable
@Preview
fun FeedHeaderPreview() {
    Box(
        Modifier
            .width(411.dp)
            .height(731.dp)
    ) {
        FeedHeader(sampleMovie) {}
    }
}