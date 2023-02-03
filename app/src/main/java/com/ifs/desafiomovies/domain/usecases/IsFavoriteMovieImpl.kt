package com.ifs.desafiomovies.domain.usecases

import com.ifs.desafiomovies.domain.repository.MovieRepository
import javax.inject.Inject

class IsFavoriteMovieImpl @Inject constructor(
    private val repository: MovieRepository
) : IsFavoriteMovie {
    override operator fun invoke():Boolean = repository.isFavorite()
}