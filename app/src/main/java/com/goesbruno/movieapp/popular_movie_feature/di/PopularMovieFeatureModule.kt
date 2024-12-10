package com.goesbruno.movieapp.popular_movie_feature.di

import com.goesbruno.movieapp.core.data.remote.MovieService
import com.goesbruno.movieapp.popular_movie_feature.data.repository.PopularMovieRepositoryImpl
import com.goesbruno.movieapp.popular_movie_feature.data.source.PopularMovieRemoteDataSourceImpl
import com.goesbruno.movieapp.popular_movie_feature.domain.repository.PopularMovieRepository
import com.goesbruno.movieapp.popular_movie_feature.domain.source.PopularMovieRemoteDataSource
import com.goesbruno.movieapp.popular_movie_feature.domain.usecase.GetPopularMoviesUseCase
import com.goesbruno.movieapp.popular_movie_feature.domain.usecase.GetPopularMoviesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PopularMovieFeatureModule {

    @Provides
    @Singleton
    fun provideMovieDataSource(service: MovieService): PopularMovieRemoteDataSource {
        return PopularMovieRemoteDataSourceImpl(service = service)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: PopularMovieRemoteDataSource): PopularMovieRepository {
        return PopularMovieRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(popularMovieRepository: PopularMovieRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCaseImpl(repository = popularMovieRepository)
    }
}