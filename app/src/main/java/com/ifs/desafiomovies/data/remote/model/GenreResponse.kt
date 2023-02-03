package com.ifs.desafiomovies.data.remote.model

import com.ifs.desafiomovies.domain.model.Genre

data class GenreResponse (
    val id: Long,
    val name: String,
) {
    fun toData(): Genre {
        return Genre(
            id = id,
            name = name,
        )
    }
}