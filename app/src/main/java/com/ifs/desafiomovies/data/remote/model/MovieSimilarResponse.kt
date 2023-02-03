package com.ifs.desafiomovies.data.remote.model

import com.ifs.desafiomovies.data.extensions.getYear
import com.ifs.desafiomovies.data.remote.helper.PosterPath.BASEURL_POSTER_PATH
import com.ifs.desafiomovies.domain.model.Genre
import com.ifs.desafiomovies.domain.model.Movie

data class MovieSimilarResponse (
    val id: Long,
    val title: String,
    val popularity: Double,
    val vote_count: Int,
    val release_date: String,
    val poster_path : String,
    val genre_ids : List<Int>
){
    fun toData(): Movie {
        return Movie(
            id = id,
            title = title,
            popularity = popularity,
            vote_count = vote_count,
            release_date = release_date.getYear(),
            poster_path = BASEURL_POSTER_PATH+poster_path,
            genres = genre_ids.map { Genre(it.toLong(),"") }
        )
    }
}
