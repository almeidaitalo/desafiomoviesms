package com.ifs.desafiomovies.data.local.datasource

interface SharedDataSource {

    fun isFavorite(): Boolean
    fun favorite()
    fun disfavor()
}