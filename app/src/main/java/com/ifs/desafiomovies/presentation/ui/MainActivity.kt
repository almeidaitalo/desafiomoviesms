package com.ifs.desafiomovies.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ifs.desafiomovies.presentation.viewmodels.MainViewModel
import com.ifs.desafiomovies.R
import com.ifs.desafiomovies.data.exception.ResponseError
import com.ifs.desafiomovies.databinding.ActivityMainBinding
import com.ifs.desafiomovies.domain.model.Movie
import com.ifs.desafiomovies.presentation.extensions.*
import com.ifs.desafiomovies.presentation.mapper.exception
import com.ifs.desafiomovies.presentation.state.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val progressDialog by lazy { createProgressDialog() }
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val moviesSimilarListAdapter by lazy { MovieSimilarListAdapter() }
    private var voteCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvMovies.adapter = moviesSimilarListAdapter
        mainViewModel.getMovieDetail()
        lifecycle(mainViewModel.uiState, ::handleGetMovie)
        observe(mainViewModel.movieData, ::handleMovie)
    }

    private fun handleGetMovie(uiState: UiState) {
        when (uiState) {
            is UiState.Loading -> {
                progressDialog.show()
            }
            is UiState.Empty -> {
                Unit
            }
            is UiState.Success -> {
                progressDialog.dismiss()
                toast("Carregado com sucesso!!")
            }
            is UiState.Failure -> {
                createDialog {
                    setMessage("ERRO AO CARREGAR!!")
                }.show()
            }
        }
    }

    private fun handleMovie(movieItemUiState: Movie) {
        setupMovieDetail(movieItemUiState)
        setupListener()
        handleGetSimilarMovies()
    }


    private fun setupMovieDetail(movie: Movie) {
        binding.tvMovieNameLg.text = movie.title
        binding.tvMoviePopularityCount.text = movie.popularity.toString()
        binding.tvMovieFavoriteCount.text = movie.vote_count.toString()

        Glide.with(binding.root.context)
            .load(movie.poster_path)
            .placeholder(R.drawable.photo_load)
            .fallback(R.drawable.broken_image)
            .into(binding.ivMoviePosterLg)
    }

    private fun setupListener() {
        binding.ivFavorite.setOnClickListener {
            if (mainViewModel.isFavorite())

            {
              binding.disfavor()
              mainViewModel.disfavor()
            }
            else{
               binding.favorite()
                mainViewModel.favorite()
            }
        }

    }

    private fun handleGetSimilarMovies() {
        lifecycleScope.launch{
            mainViewModel.moviesSimilarPaging().collectLatest { response ->
                moviesSimilarListAdapter.submitData(response)
            }
        }
    }



}