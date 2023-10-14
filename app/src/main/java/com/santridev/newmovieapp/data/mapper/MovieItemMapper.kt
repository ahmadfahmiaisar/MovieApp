package com.santridev.newmovieapp.data.mapper

import com.santridev.newmovieapp.base.abstraction.Mapper
import com.santridev.newmovieapp.data.dto.MovieItemDTO
import com.santridev.newmovieapp.domain.model.Movie
import javax.inject.Inject

class MovieItemMapper @Inject constructor() : Mapper<List<MovieItemDTO>, List<Movie>> {
    override fun map(input: List<MovieItemDTO>): List<Movie> {
        return input.map { movie ->
            with(movie) {
                Movie(
                    id = id ?: 0,
                    title = title.orEmpty(),
                    originalTitle = originalTitle.orEmpty(),
                    overview = overview.orEmpty(),
                    posterPath = posterPath.orEmpty(),
                    backdropPath = backdropPath.orEmpty(),
                    voteCount = voteCount ?: 0,
                    voteAverage = voteAverage ?: 0.0f,
                    popularity = popularity ?: 0.0f,
                    releaseDate = releaseDate.orEmpty()
                )
            }

        }
    }
}