package com.santridev.newmovieapp.data.dto

import com.squareup.moshi.Json

data class MoviesDTO(
    @field:Json(name = "page")
    val page: Int? = null,

    @field:Json(name = "results")
    val results: List<MovieItemDTO?>? = null,

    @field:Json(name = "total_pages")
    val totalPages: Int? = null,

    @field:Json(name = "total_results")
    val totalResults: Int? = null
)
