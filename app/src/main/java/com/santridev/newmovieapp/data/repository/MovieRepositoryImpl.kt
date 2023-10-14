package com.santridev.newmovieapp.data.repository

import com.santridev.newmovieapp.base.state.Either
import com.santridev.newmovieapp.data.mapper.MovieDetailMapper
import com.santridev.newmovieapp.data.mapper.MovieItemMapper
import com.santridev.newmovieapp.data.mapper.MovieMapper
import com.santridev.newmovieapp.data.source.MovieLocalDataSource
import com.santridev.newmovieapp.data.source.MovieRemoteDataSource
import com.santridev.newmovieapp.domain.model.Movie
import com.santridev.newmovieapp.domain.model.MovieDetail
import com.santridev.newmovieapp.domain.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
    private val movieMapper: MovieMapper,
    private val movieItemMapper: MovieItemMapper,
    private val movieDetailMapper: MovieDetailMapper
) : MovieRepository {
    override suspend fun getMovieNowPlaying(page: Int): Either<Exception, List<Movie>> {
        return when (val result = remoteDataSource.getMovieNowPlaying(page)) {
            is Either.Failure -> Either.Failure(result.cause)
            is Either.Success -> Either.Success(movieMapper.map(result.data))
        }
    }

    override suspend fun getMoviePopular(page: Int): Either<Exception, List<Movie>> {
        return when (val result = remoteDataSource.getMoviePopular(page)) {
            is Either.Failure -> Either.Failure(result.cause)
            is Either.Success -> Either.Success(movieMapper.map(result.data))
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Either<Exception, MovieDetail> {
        return when (val result = remoteDataSource.getMovieDetail(movieId)) {
            is Either.Failure -> Either.Failure(result.cause)
            is Either.Success -> Either.Success(movieDetailMapper.map(result.data))
        }
    }

    override suspend fun getAllFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getAllFavoriteMovie().map { movieItemMapper.map(it) }
    }

}
