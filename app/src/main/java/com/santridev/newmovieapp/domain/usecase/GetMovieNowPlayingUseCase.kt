package com.santridev.newmovieapp.domain.usecase

import com.santridev.newmovieapp.base.dispatcher.DispatcherProvider
import com.santridev.newmovieapp.base.extension.toLocalDate
import com.santridev.newmovieapp.base.state.Either
import com.santridev.newmovieapp.domain.model.Movie
import com.santridev.newmovieapp.domain.model.MovieUiModel
import com.santridev.newmovieapp.domain.repository.MovieRepository
import com.santridev.newmovieapp.domain.resource.MovieResource
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetMovieNowPlayingUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val dispatcher: DispatcherProvider
) {
    suspend operator fun invoke(page: Int): Flow<MovieResource> {
        return flow {
            emit(MovieResource.Loading)
            when (val result = repository.getMovieNowPlaying(page)) {
                is Either.Success -> emit(MovieResource.Success(result.data.map { it.mapToUiModel() }))
                is Either.Failure -> emit(MovieResource.Error(result.cause))
            }
        }.flowOn(dispatcher.io)
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