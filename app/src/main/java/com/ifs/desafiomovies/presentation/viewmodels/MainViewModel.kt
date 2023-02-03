package com.ifs.desafiomovies.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ifs.desafiomovies.core.Either
import com.ifs.desafiomovies.data.exception.ResponseError
import com.ifs.desafiomovies.domain.model.Movie
import com.ifs.desafiomovies.domain.usecases.*
import com.ifs.desafiomovies.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovie: GetMovie,
    private val getSimilarMovies: GetSimilarMovies,
    private val favoriteMovie: FavoriteMovie,
    private val disfavorMovie: DisfavorMovie,
    private val isFavoriteMovie: IsFavoriteMovie
): ViewModel(){
    private val _movieData: MutableLiveData<Movie> = MutableLiveData()
    val movieData: LiveData<Movie> get() = _movieData

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Empty)
    val uiState: StateFlow<UiState> get() = _uiState


    fun getMovieDetail(){
       viewModelScope.launch {
            _uiState.update { UiState.Loading }
           when(val result = getMovie()) {
               is Either.Success ->  {
                   _movieData.value = result.data!!
                   _uiState.update { UiState.Success }
               }
               is Either.Failure -> _uiState.update {
                   UiState.Failure(result.cause as ResponseError)
               }
           }
       }
    }

    fun favorite() = favoriteMovie()
    fun disfavor() = disfavorMovie()
    fun isFavorite():Boolean = isFavoriteMovie()

    suspend fun moviesSimilarPaging() = getSimilarMovies.invoke().cachedIn(viewModelScope)

}