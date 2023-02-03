package com.ifs.desafiomovies.domain.usecases

import androidx.paging.PagingData
import com.ifs.desafiomovies.domain.model.Movie
import com.ifs.desafiomovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarMoviesImpl @Inject constructor(
    private val repository: MovieRepository
    ): GetSimilarMovies{
    override suspend fun invoke(): Flow<PagingData<Movie>> {
        return repository.getSimilarMovies()
    }
    }
