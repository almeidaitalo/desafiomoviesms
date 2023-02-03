package com.ifs.desafiomovies.domain.usecases

import com.ifs.desafiomovies.domain.repository.MovieRepository
import javax.inject.Inject

class DisfavorMovieImpl @Inject constructor(
    private val repository: MovieRepository
) : DisfavorMovie {
    override operator fun invoke() = repository.disfavor()
}