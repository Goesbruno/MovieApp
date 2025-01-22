package com.goesbruno.movieapp.movie_detail_feature.data.source

import com.goesbruno.movieapp.core.data.remote.MovieService
import com.goesbruno.movieapp.core.data.remote.response.MovieResponse
import com.goesbruno.movieapp.core.domain.model.MovieDetails
import com.goesbruno.movieapp.core.paging.SimilarMoviePagingSource
import com.goesbruno.movieapp.core.util.toBackdropUrl
import com.goesbruno.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import javax.inject.Inject

class MovieDetailRemoteDataSourceImpl @Inject constructor(
    private val service: MovieService
): MovieDetailRemoteDataSource {

    override suspend fun getMovieDetail(movieId: Int): MovieDetails {
        val response = service.getMovie(movieId)
        val genres = response.genres.map { it.name }
        return MovieDetails(
            id = response.id,
            title = response.title,
            overview = response.overview,
            genres = genres,
            releaseDate = response.releaseDate,
            backdropPathUrl = response.backdropPath.toBackdropUrl(),
            voteAverage = response.voteAverage,
            duration = response.runtime,
            voteCount = response.voteCount
        )
    }

    override suspend fun getSimilarMovies(
        page: Int,
        movieId: Int
    ): MovieResponse {
        return service.getSimilarMovies(page = page, movieId = movieId)
    }

    override fun getSimilarMoviesPagingSource(movieId: Int): SimilarMoviePagingSource {
        return SimilarMoviePagingSource(remoteDataSource = this, movieId = movieId)
    }

}