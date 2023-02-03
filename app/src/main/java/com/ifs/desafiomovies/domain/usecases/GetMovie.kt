package com.ifs.desafiomovies.domain.usecases

import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.domain.model.Movie

interface GetMovie {
    suspend operator fun invoke(): Either<Movie, Exception>
}