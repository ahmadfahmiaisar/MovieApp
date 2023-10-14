package com.santridev.newmovieapp.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath : String,
    val backdropPath : String,
    val voteCount: Int,
    val voteAverage: Float,
    val popularity: Float,
    val releaseDate: String,
)
