package com.ifs.desafiomovies.domain.repository

import androidx.paging.PagingData
import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovie(): Either<Movie, Exception>
    suspend fun getSimilarMovies(): Flow<PagingData<Movie>>
    fun favorite()
    fun disfavor()
    fun isFavorite(): Boolean
}