package com.goesbruno.movieapp.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.goesbruno.movieapp.core.domain.model.Movie
import com.goesbruno.movieapp.popular_movie_feature.data.mapper.toMovies
import com.goesbruno.movieapp.popular_movie_feature.domain.source.PopularMovieRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val remoteDataSource: PopularMovieRemoteDataSource
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            val moviePaging = remoteDataSource.getPopularMovies(page = pageNumber)

            val movies = moviePaging.movies
            val totalPages = moviePaging.totalPages

            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (pageNumber == totalPages) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            exception.printStackTrace()
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val LIMIT = 20
    }

}