package com.santridev.newmovieapp.domain.usecase

import com.santridev.newmovieapp.base.state.Either
import com.santridev.newmovieapp.domain.model.Movie
import com.santridev.newmovieapp.domain.model.MovieUiModel
import com.santridev.newmovieapp.domain.repository.MovieRepository
import com.santridev.newmovieapp.base.extension.toLocalDate
import com.santridev.newmovieapp.domain.resource.MovieResource
import javax.inject.Inject

class GetMoviePopularUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(page: Int): MovieResource {
        return when (val result = repository.getMoviePopular(page)) {
            is Either.Success -> MovieResource.Success(result.data.map { it.mapToUiModel() })
            is Either.Failure -> MovieResource.Error(result.cause)
        }
    }

    private fun Movie.mapToUiModel(): MovieUiModel {
        return MovieUiModel(
            id,
            title,
            originalTitle,
            overview,
            posterPath,
            backdropPath,
            voteCount.toString(),
            voteAverage.toString(),
            popularity.toString(),
            releaseDate.toLocalDate()
        )
    }
}