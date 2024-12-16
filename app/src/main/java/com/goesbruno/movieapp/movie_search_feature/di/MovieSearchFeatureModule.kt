package com.goesbruno.movieapp.movie_search_feature.di

import com.goesbruno.movieapp.core.data.remote.MovieService
import com.goesbruno.movieapp.movie_search_feature.data.repository.MovieSearchRepositoryImpl
import com.goesbruno.movieapp.movie_search_feature.data.source.MovieSearchRemoteDataSourceImpl
import com.goesbruno.movieapp.movie_search_feature.domain.repository.MovieSearchRepository
import com.goesbruno.movieapp.movie_search_feature.domain.source.MovieSearchRemoteDataSource
import com.goesbruno.movieapp.movie_search_feature.domain.usecases.GetMovieSearchUseCase
import com.goesbruno.movieapp.movie_search_feature.domain.usecases.GetMovieSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieSearchFeatureModule {

    @Provides
    @Singleton
    fun provideMovieSearchDataSource(service: MovieService): MovieSearchRemoteDataSource {
        return MovieSearchRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(remoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository {
        return MovieSearchRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieSearchUseCase(repository: MovieSearchRepository): GetMovieSearchUseCase {
        return GetMovieSearchUseCaseImpl(repository = repository)
    }
}