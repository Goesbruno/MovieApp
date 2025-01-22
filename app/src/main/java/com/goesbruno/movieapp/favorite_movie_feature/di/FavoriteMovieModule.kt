package com.goesbruno.movieapp.favorite_movie_feature.di

import com.goesbruno.movieapp.core.data.local.dao.MovieDao
import com.goesbruno.movieapp.favorite_movie_feature.data.repository.FavoriteMovieRepositoryImpl
import com.goesbruno.movieapp.favorite_movie_feature.data.source.FavoriteMovieLocalDataSourceImpl
import com.goesbruno.movieapp.favorite_movie_feature.domain.repository.FavoriteMovieRepository
import com.goesbruno.movieapp.favorite_movie_feature.domain.source.FavoriteMovieLocalDataSource
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.AddFavoriteMovieUseCase
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.AddFavoriteMovieUseCaseImpl
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.DeleteFavoriteMovieUseCase
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.DeleteFavoriteMovieUseCaseImpl
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.GetFavoriteMoviesUseCase
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.GetFavoriteMoviesUseCaseImpl
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.IsFavoriteMovieUseCase
import com.goesbruno.movieapp.favorite_movie_feature.domain.usecase.IsFavoriteMovieUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteMovieModule {

    @Provides
    @Singleton
    fun provideFavoriteMovieLocalDataSource(dao: MovieDao): FavoriteMovieLocalDataSource {
        return FavoriteMovieLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieRepository(localDataSource: FavoriteMovieLocalDataSource): FavoriteMovieRepository {
        return FavoriteMovieRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetFavoriteMoviesUseCase(repository: FavoriteMovieRepository): GetFavoriteMoviesUseCase{
        return GetFavoriteMoviesUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideAddFavoriteMovieUseCase(repository: FavoriteMovieRepository): AddFavoriteMovieUseCase {
        return AddFavoriteMovieUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteFavoriteMovieUseCase(repository: FavoriteMovieRepository): DeleteFavoriteMovieUseCase {
        return DeleteFavoriteMovieUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideIsFavoriteMovieUseCase(repository: FavoriteMovieRepository): IsFavoriteMovieUseCase {
        return IsFavoriteMovieUseCaseImpl(repository)
    }

}