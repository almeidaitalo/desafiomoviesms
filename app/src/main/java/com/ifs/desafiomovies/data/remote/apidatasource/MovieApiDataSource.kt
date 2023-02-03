package com.ifs.desafiomovies.data.remote.apidatasource

import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.domain.model.Genre
import com.ifs.desafiomovies.domain.model.Movie


interface MovieApiDataSource {

    suspend fun getMovie(): Either<Movie, Exception>
    suspend fun getAllGenres(): Either<List<Genre>, Exception>
}