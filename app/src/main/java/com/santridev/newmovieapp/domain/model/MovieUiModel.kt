package com.santridev.newmovieapp.domain.model

data class MovieUiModel(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath : String,
    val backdropPath : String,
    val voteCount: String,
    val voteAverage: String,
    val popularity: String,
    val releaseDate: String,
)
