package com.ifs.desafiomovies.data.local.datasource

import com.ifs.desafiomovies.data.local.datasource.Preferences
import com.ifs.desafiomovies.data.local.datasource.SharedDataSource
import javax.inject.Inject


class SharedDataSourceImpl @Inject constructor(private val preferences: Preferences):
    SharedDataSource {

    override fun isFavorite(): Boolean {
        return preferences.isFavorite()
    }

    override fun favorite() {
        preferences.favorite()
    }

    override fun disfavor() {
        preferences.disfavor()
    }

}