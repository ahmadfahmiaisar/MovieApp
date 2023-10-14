package com.santridev.newmovieapp.data.mapper

import com.santridev.newmovieapp.base.abstraction.Mapper
import com.santridev.newmovieapp.data.dto.MovieItemDTO
import com.santridev.newmovieapp.data.dto.MoviesDTO
import com.santridev.newmovieapp.domain.model.Movie
import javax.inject.Inject

class MovieMapper @Inject constructor() : Mapper<MoviesDTO, List<Movie>> {
    override fun map(input: MoviesDTO): List<Movie> {
        return input.results?.map {
            Movie(
                id = it?.id ?: 0,
                title = it?.title.orEmpty(),
                originalTitle = it?.originalTitle.orEmpty(),
                overview = it?.overview.orEmpty(),
                posterPath = it?.posterPath.orEmpty(),
                backdropPath = it?.backdropPath.orEmpty(),
                voteCount = it?.voteCount ?: 0,
                voteAverage = it?.voteAverage ?: 0.0f,
                popularity = it?.popularity ?: 0.0f,
                releaseDate = it?.releaseDate.orEmpty()
            )
        } ?: emptyList()
    }
}
