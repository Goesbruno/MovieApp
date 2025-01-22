package com.goesbruno.movieapp.movie_detail_feature.di

import com.goesbruno.movieapp.core.data.remote.MovieService
import com.goesbruno.movieapp.movie_detail_feature.data.repository.MovieDetailsRepositoryImpl
import com.goesbruno.movieapp.movie_detail_feature.data.source.MovieDetailRemoteDataSourceImpl
import com.goesbruno.movieapp.movie_detail_feature.domain.repository.MovieDetailsRepository
import com.goesbruno.movieapp.movie_detail_feature.domain.source.MovieDetailRemoteDataSource
import com.goesbruno.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import com.goesbruno.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {

    @Provides
    @Singleton
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailRemoteDataSource {
        return MovieDetailRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(remoteDataSource: MovieDetailRemoteDataSource): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MovieDetailsRepository): GetMovieDetailsUseCase {
        return GetMovieDetailsUseCaseImpl(repository = repository)
    }

}