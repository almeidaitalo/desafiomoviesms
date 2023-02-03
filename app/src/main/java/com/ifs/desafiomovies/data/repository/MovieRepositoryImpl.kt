package com.ifs.desafiomovies.data.repository


import androidx.paging.*
import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.data.local.datasource.SharedDataSource
import com.ifs.desafiomovies.data.remote.apidatasource.MovieApiDataSource
import com.ifs.desafiomovies.data.remote.apidatasource.retrofit.MoviePagingApiDataSourceRetro
import com.ifs.desafiomovies.domain.model.Genre
import com.ifs.desafiomovies.domain.model.Movie
import com.ifs.desafiomovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (
    private val sharedDataSource: SharedDataSource,
    private val movieApiDataSource: MovieApiDataSource,
    private val moviePagingApiDataSourceRetro: MoviePagingApiDataSourceRetro
):
    MovieRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override suspend fun getMovie(): Either<Movie, Exception> {
        return movieApiDataSource.getMovie()
    }

    override suspend fun getSimilarMovies(): Flow<PagingData<Movie>> {
        val genres = movieApiDataSource.getAllGenres()
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE)) {
            moviePagingApiDataSourceRetro
        }.flow.map { it.setGenre(
            when(val result = genres){
                is Either.Success ->  { result.data}
                else -> {null}
            })}
    }

    override fun favorite() {
        sharedDataSource.favorite()


    }

    override fun disfavor() {
        sharedDataSource.disfavor()

    }

    override fun isFavorite(): Boolean {
      return sharedDataSource.isFavorite()
    }

    private fun PagingData<Movie>.setGenre(list: List<Genre>?):PagingData<Movie>{
        return if (list == null) this
        else this.map { movies ->
                list.forEach { genre ->
                    movies.genres.find { movieGenre ->
                        movieGenre.id == genre.id
                    }?.name = genre.name
                }
                movies
        }
    }
}

