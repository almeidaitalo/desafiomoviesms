package com.ifs.desafiomovies.data.remote.service

import com.ifs.desafiomovies.data.remote.model.MovieResponse
import com.ifs.desafiomovies.data.remote.model.ResultGenreResponse
import com.ifs.desafiomovies.data.remote.model.ResultMovieSimilarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    companion object{
        const val MOVIE_ID = 40096
    }
    @GET("movie/$MOVIE_ID")
    suspend fun getMovie(): MovieResponse

    @GET("movie/$MOVIE_ID/similar")
    suspend fun getSimilarMovies(@Query("page") page: Int): Response<ResultMovieSimilarResponse>

    @GET("genre/movie/list")
    suspend fun getAllGenres(): ResultGenreResponse
}