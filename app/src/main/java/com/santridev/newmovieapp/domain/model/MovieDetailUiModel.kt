package com.santridev.newmovieapp.domain.model

data class MovieDetailUiModel(
    val id: Int,
    val title: String,
    val originalTitle: String,
    val originalLanguage : String,
    val overview: String,
    val posterPath : String,
    val backdropPath : String,
    val voteCount: String,
    val voteAverage: String,
    val popularity: String,
    val releaseDate: String,
    val adult: Boolean,
    val runtime : String,
    val tagline : String,
    val status : String,
    val genres : List<MovieDetail.Genre>
)
