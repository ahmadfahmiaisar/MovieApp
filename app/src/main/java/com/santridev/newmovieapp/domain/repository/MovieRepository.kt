package com.santridev.newmovieapp.domain.repository

import com.santridev.newmovieapp.base.state.Either
import com.santridev.newmovieapp.domain.model.Movie
import com.santridev.newmovieapp.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieNowPlaying(page: Int): Either<Exception, List<Movie>>
    suspend fun getMoviePopular(page: Int): Either<Exception, List<Movie>>
    suspend fun getMovieDetail(movieId: Int): Either<Exception, MovieDetail>

    suspend fun getAllFavoriteMovies(): Flow<List<Movie>>
}