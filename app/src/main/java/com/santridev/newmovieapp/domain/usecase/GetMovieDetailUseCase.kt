package com.santridev.newmovieapp.domain.usecase

import com.santridev.newmovieapp.base.extension.toLocalDate
import com.santridev.newmovieapp.base.state.Either
import com.santridev.newmovieapp.domain.model.MovieDetail
import com.santridev.newmovieapp.domain.model.MovieDetailUiModel
import com.santridev.newmovieapp.domain.repository.MovieRepository
import com.santridev.newmovieapp.domain.resource.MovieDetailResource
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: Int): MovieDetailResource {
        return when (val result = repository.getMovieDetail(movieId)) {
            is Either.Success -> MovieDetailResource.Success(mapData(result.data))
            is Either.Failure -> MovieDetailResource.Error(result.cause)
        }
    }

    private fun mapData(input: MovieDetail) : MovieDetailUiModel {
        return MovieDetailUiModel(
            input.id,
            input.title,
            input.originalTitle,
            input.originalLanguage,
            input.overview,
            input.posterPath,
            input.backdropPath,
            input.voteCount.toString(),
            input.voteAverage.toString(),
            input.popularity.toString(),
            transformReleaseDate(input.releaseDate),
            input.adult,
            transformRuntime(input.runtime),
            input.tagline,
            input.status,
            input.genres
        )
    }
    private fun transformReleaseDate(releaseDate: String) : String {
        return releaseDate.toLocalDate()
    }

    private fun transformRuntime(runtime: Int) : String {
        return "$runtime min"
    }
}