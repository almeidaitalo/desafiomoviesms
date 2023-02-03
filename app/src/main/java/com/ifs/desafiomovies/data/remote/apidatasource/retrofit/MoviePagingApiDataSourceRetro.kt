package com.ifs.desafiomovies.data.remote.apidatasource.retrofit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ifs.desafiomovies.data.exception.ResponseError
import com.ifs.desafiomovies.data.remote.model.ResultMovieSimilarResponse
import com.ifs.desafiomovies.data.remote.helper.safeCall
import com.ifs.desafiomovies.data.remote.service.MovieService
import com.ifs.desafiomovies.domain.model.Movie
import javax.inject.Inject

class MoviePagingApiDataSourceRetro @Inject constructor(
    private val movieService: MovieService
) : PagingSource<Int, Movie>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: DEFAULT_INDEX
        return when (val response = safeCall {
            movieService.getSimilarMovies(page)}) {
            is ResultMovieSimilarResponse -> {
                LoadResult.Page(
                    response.results.map{it.toData()},
                    prevKey = null,
                    nextKey = response.page+1
                )
            }
            else -> {
                LoadResult.Error(response as ResponseError)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    companion object {
        private const val DEFAULT_INDEX= 1
    }

}