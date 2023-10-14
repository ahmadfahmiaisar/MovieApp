package com.santridev.newmovieapp.domain.resource

import com.santridev.newmovieapp.domain.model.MovieUiModel

sealed class MovieResource {
    object Loading : MovieResource()
    data class Success(val data: List<MovieUiModel>) : MovieResource()
    data class Error(val cause: Exception) : MovieResource()
}
