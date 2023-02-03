package com.ifs.desafiomovies.data.remote.model

import com.ifs.desafiomovies.data.remote.model.MovieSimilarResponse

data class ResultMovieSimilarResponse(
    val page: Int = 0,
    val results: List<MovieSimilarResponse> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)