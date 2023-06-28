package com.kabiplayer.extensions

import com.kabiplayer.data_models.Media

fun Media.getId(): Int {
    return when (this) {
        is Media.Movie -> id
        is Media.Tv -> id
        else -> -1
    }
}