package com.ifs.desafiomovies.domain.usecases

import com.ifs.desafiomovies.domain.repository.MovieRepository
import javax.inject.Inject

class FavoriteMovieImpl @Inject constructor(
    private val repository: MovieRepository
) : FavoriteMovie {
    override operator fun invoke() = repository.favorite()
}