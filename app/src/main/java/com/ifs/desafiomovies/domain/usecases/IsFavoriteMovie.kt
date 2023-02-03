package com.ifs.desafiomovies.domain.usecases

interface IsFavoriteMovie {
    operator fun invoke(): Boolean
}