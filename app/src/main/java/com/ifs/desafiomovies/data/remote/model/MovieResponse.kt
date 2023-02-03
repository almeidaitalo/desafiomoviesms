package com.ifs.desafiomovies.data.remote.model

import com.ifs.desafiomovies.data.extensions.getYear
import com.ifs.desafiomovies.data.remote.helper.PosterPath.BASEURL_POSTER_PATH
import com.ifs.desafiomovies.domain.model.Movie

data class MovieResponse (
    val id: Long = 0,
    val title: String = "",
    val popularity: Double = 0.0,
    val vote_count: Int =0,
    val release_date: String = "",
    val poster_path : String= ""
){
        fun toData(): Movie{
            return Movie(
                id = id,
                title = title,
                popularity = popularity,
                vote_count = vote_count,
                release_date = release_date.getYear(),
                poster_path = BASEURL_POSTER_PATH+poster_path
            )
        }
}