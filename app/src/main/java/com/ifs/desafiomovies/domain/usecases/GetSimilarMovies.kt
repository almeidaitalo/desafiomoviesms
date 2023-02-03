package com.ifs.desafiomovies.domain.usecases

import androidx.paging.PagingData
import com.ifs.desafiomovies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetSimilarMovies {
    suspend operator fun invoke(): Flow<PagingData<Movie>>
}