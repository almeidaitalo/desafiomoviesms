package com.ifs.desafiomovies.domain.usecases

import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.domain.model.Movie
import com.ifs.desafiomovies.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMovie {
    override suspend operator fun invoke(): Either<Movie, Exception> {
       return  repository.getMovie()
    }

}