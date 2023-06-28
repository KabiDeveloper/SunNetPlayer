package com.kabiplayer.extensions

import android.annotation.SuppressLint
import com.kabiplayer.constants.IMAGE_BASE_URL
import com.kabiplayer.constants.ImageSize
import com.kabiplayer.data_models.IMovie
import com.kabiplayer.data_models.MediaBsData
import java.text.SimpleDateFormat
import java.util.*

fun IMovie.getPosterUrl(size: ImageSize = ImageSize.NORMAL): String {
    return "$IMAGE_BASE_URL${size.value}${this.posterPath}"
}


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")


@SuppressLint("SimpleDateFormat")
fun IMovie.getReleaseYear(): String? {
    return if (this.releaseDate == null) {
        null
    } else {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = format.parse(this.releaseDate)
        val df = SimpleDateFormat("yyyy")
        val year = df.format(date)
        year
    }
}

fun IMovie.toMediaBsData(): MediaBsData {
    return MediaBsData(
        "movie",
        this.id,
        this.getPosterUrl(),
        this.title,
        this.getReleaseYear(),
        this.overview
    )
}