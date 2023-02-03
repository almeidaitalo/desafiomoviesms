package com.ifs.desafiomovies.domain.di

import com.ifs.desafiomovies.domain.repository.MovieRepository
import com.ifs.desafiomovies.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetMovie(movieRepository: MovieRepository): GetMovie = GetMovieImpl(movieRepository)

    @Provides
    fun provideSimilarMovie(movieRepository: MovieRepository): GetSimilarMovies = GetSimilarMoviesImpl(movieRepository)

    @Provides
    fun provideFavoriteMovie(movieRepository: MovieRepository): FavoriteMovie = FavoriteMovieImpl(movieRepository)

    @Provides
    fun provideDisfavorMovie(movieRepository: MovieRepository): DisfavorMovie = DisfavorMovieImpl(movieRepository)

    @Provides
    fun provideIsFavoriteMovie(movieRepository: MovieRepository): IsFavoriteMovie = IsFavoriteMovieImpl(movieRepository)

}