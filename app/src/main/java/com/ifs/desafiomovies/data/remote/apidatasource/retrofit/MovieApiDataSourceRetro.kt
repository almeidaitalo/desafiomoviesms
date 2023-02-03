package com.ifs.desafiomovies.data.remote.apidatasource.retrofit


import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.data.exception.ResponseError
import com.ifs.desafiomovies.data.remote.apidatasource.MovieApiDataSource
import com.ifs.desafiomovies.data.remote.service.MovieService
import com.ifs.desafiomovies.domain.model.Genre
import com.ifs.desafiomovies.domain.model.Movie
import javax.inject.Inject

class MovieApiDataSourceRetro @Inject constructor(
    private val movieService: MovieService
) : MovieApiDataSource {

    override suspend fun getMovie(): Either<Movie, Exception> {
        return try {
            Either.Success(movieService.getMovie().toData())
        }
        catch (e: Exception){
            Either.Failure(ResponseError.IOErrorException)
        }
    }
    override suspend fun getAllGenres(): Either<List<Genre>, Exception>{
        return try {
            Either.Success(movieService.getAllGenres().genres.map { it.toData() })
        }
        catch (e: Exception){
            Either.Failure(ResponseError.IOErrorException)
        }
    }
}