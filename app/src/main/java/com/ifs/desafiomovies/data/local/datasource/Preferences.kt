package com.ifs.desafiomovies.data.local.datasource

import android.content.Context

class Preferences(context: Context) {

    companion object {
        private const val FAVORITE = "FAVORITE"
    }

    private val preferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)

    fun favorite() {

    }
    fun disfavor() {
        val edit = preferences.edit()
        edit.putBoolean(FAVORITE, false)
        edit.apply()
    }

    fun isFavorite(): Boolean {
        return preferences.getBoolean(FAVORITE, false)
    }
}