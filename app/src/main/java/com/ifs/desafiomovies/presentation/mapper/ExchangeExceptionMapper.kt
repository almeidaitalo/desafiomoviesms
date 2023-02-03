package com.ifs.desafiomovies.presentation.mapper

import android.app.Activity
import com.ifs.desafiomovies.R
import com.ifs.desafiomovies.data.exception.ResponseError

fun Activity.exception(exception: Exception):String{
    return when(exception){
        is ResponseError.IOErrorException -> "Error"
        else -> String()
    }
}