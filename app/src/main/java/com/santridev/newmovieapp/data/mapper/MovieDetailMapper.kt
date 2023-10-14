package com.santridev.newmovieapp.data.mapper

import com.santridev.newmovieapp.base.abstraction.Mapper
import com.santridev.newmovieapp.data.dto.MovieDetailDTO
import com.santridev.newmovieapp.domain.model.MovieDetail
import javax.inject.Inject

class MovieDetailMapper @Inject constructor(): Mapper<MovieDetailDTO, MovieDetail> {
    override fun map(input: MovieDetailDTO): MovieDetail {
        return MovieDetail(
            input.id ?: 0,
            input.title ?: "",
            input.originalTitle ?: "",
            input.originalLanguage ?: "",
            input.overview ?: "",
            input.posterPath ?: "",
            input.backdropPath ?: "",
            input.voteCount ?: 0,
            input.voteAverage ?: 0.0f,
            input.popularity ?: 0.0f,
            input.releaseDate ?: "",
            input.adult ?: false,
            input.runtime ?: 0,
            input.tagline ?: "",
            input.status ?: "",
            mapGenres(input.genres)
        )
    }

    private fun mapGenres(input: List<MovieDetailDTO.Genre?>?) : List<MovieDetail.Genre>{
        val genres = mutableListOf<MovieDetail.Genre>()
        input?.forEach {
            genres.add(MovieDetail.Genre(it?.id ?: 0, it?.name ?: ""))
        }
        return genres
    }
}