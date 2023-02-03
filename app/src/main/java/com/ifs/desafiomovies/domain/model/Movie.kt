package com.ifs.desafiomovies.domain.model


data class Movie (
    val id: Long,
    val title: String,
    val popularity: Double,
    val vote_count: Int,
    val release_date: String,
    val poster_path: String,
    var genres: List<Genre> = listOf()
)