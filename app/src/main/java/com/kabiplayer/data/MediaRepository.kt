package com.kabiplayer.data

import com.kabiplayer.network.services.ApiClient


object MediaRepository {

    suspend fun fetchTrending(timeWindow: String = "week", page: Int = 1) =
        ApiClient.TMDB.fetchTrending(timeWindow, page)

    suspend fun fetchMovieDetails(id: Int) = ApiClient.TMDB.fetchMovieDetails(id)


    suspend fun fetchPopularMovies(page: Int) = ApiClient.TMDB.fetchPopularMovies(page)

    suspend fun fetchPopularTvShows(page: Int) = ApiClient.TMDB.fetchPopularTvShows(page)

    suspend fun discoverMovies(
        withGenres: String? = null,
        sortBy: String? = null,
        voteCountGreater: Int? = null,
        withWatchProviders: Int? = null,
        watchRegion: String? = null,
    ) = ApiClient.TMDB.discoverMovies(
        withGenres,
        sortBy,
        voteCountGreater,
        withWatchProviders,
        watchRegion
    )

    suspend fun discoverTvShows(
        withGenres: String? = null,
        sortBy: String? = null,
        voteCountGreater: Int? = null,
        withWatchProviders: Int? = null,
        watchRegion: String? = null,
    ) = ApiClient.TMDB.discoverTvShows(
        withGenres,
        sortBy,
        voteCountGreater,
        withWatchProviders,
        watchRegion
    )

}