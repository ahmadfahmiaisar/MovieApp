package com.santridev.newmovieapp.domain.resource

import com.santridev.newmovieapp.domain.model.MovieDetailUiModel

sealed class MovieDetailResource {
    object Loading : MovieDetailResource()
    data class Success(val data: MovieDetailUiModel) : MovieDetailResource()
    data class Error(val cause: Exception) : MovieDetailResource()
}
