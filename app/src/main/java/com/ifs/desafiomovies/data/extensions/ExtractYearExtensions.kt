package com.ifs.desafiomovies.data.extensions

fun String.getYear(): String{
    return this.split("-")[0]
}