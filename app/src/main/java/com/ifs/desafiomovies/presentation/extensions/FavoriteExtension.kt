package com.ifs.desafiomovies.presentation.extensions

import com.ifs.desafiomovies.R
import com.ifs.desafiomovies.databinding.ActivityMainBinding


fun ActivityMainBinding.favorite(){
    this.ivFavorite.setImageResource(R.drawable.favorite_full_24)
}

fun ActivityMainBinding.disfavor(){
    this.ivFavorite.setImageResource(R.drawable.favorite_empty_24)
}
