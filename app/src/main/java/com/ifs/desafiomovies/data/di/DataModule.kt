package com.ifs.desafiomovies.data.di


import android.content.Context
import com.ifs.desafiomovies.core.ConfigAPI.API_TOKEN
import com.ifs.desafiomovies.core.ConfigAPI.BASE_URL
import com.ifs.desafiomovies.core.ConfigAPI.TIME_CONNECT
import com.ifs.desafiomovies.core.ConfigAPI.TIME_READ
import com.ifs.desafiomovies.data.local.datasource.Preferences
import com.ifs.desafiomovies.data.local.datasource.SharedDataSource
import com.ifs.desafiomovies.data.local.datasource.SharedDataSourceImpl
import com.ifs.desafiomovies.data.remote.apidatasource.MovieApiDataSource
import com.ifs.desafiomovies.data.remote.apidatasource.retrofit.MovieApiDataSourceRetro
import com.ifs.desafiomovies.data.remote.apidatasource.retrofit.MoviePagingApiDataSourceRetro
import com.ifs.desafiomovies.data.remote.service.MovieService
import com.ifs.desafiomovies.data.repository.MovieRepositoryImpl
import com.ifs.desafiomovies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideIntercept(): Interceptor{
        return Interceptor { chain ->
            val builder = chain.request().newBuilder()
            builder.header("Authorization","Bearer $API_TOKEN")
            return@Interceptor chain.proceed(builder.build())
        }
    }

    @Provides
    fun provideOkHttpBuilder(auth: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(TIME_CONNECT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIME_READ.toLong(), TimeUnit.SECONDS)
                .addInterceptor(auth)
                .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit ) : MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun providePreferences(context: Context): Preferences = Preferences(context)

    @Singleton
    @Provides
    fun provideShareDataSource(
        preferences: Preferences
    ): SharedDataSource = SharedDataSourceImpl(preferences)

    @Singleton
    @Provides
    fun provideMovieApiDataSource(
            movieService: MovieService
    ): MovieApiDataSource = MovieApiDataSourceRetro(movieService)

    @Singleton
    @Provides
    fun provideMoviePagingApiDataSource(
        movieService: MovieService
    ): MoviePagingApiDataSourceRetro = MoviePagingApiDataSourceRetro(movieService)

    @Singleton
    @Provides
    fun provideMovieRepository(
        sharedDataSource: SharedDataSource,
        movieApiDataSource: MovieApiDataSource,
        moviePagingApiDataSourceRetro: MoviePagingApiDataSourceRetro
    ): MovieRepository = MovieRepositoryImpl(sharedDataSource ,movieApiDataSource, moviePagingApiDataSourceRetro)


}