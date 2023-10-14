package com.santridev.newmovieapp.data.source

import com.santridev.newmovieapp.base.apiclient.ApiClient
import com.santridev.newmovieapp.base.state.ApiResponse
import com.santridev.newmovieapp.base.state.Either
import com.santridev.newmovieapp.data.dto.MovieDetailDTO
import com.santridev.newmovieapp.data.dto.MoviesDTO
import com.santridev.newmovieapp.data.service.MovieServiceApi
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiClient: ApiClient, private val movieServiceApi: MovieServiceApi
) {
    suspend fun getMovieNowPlaying(page: Int): Either<Exception, MoviesDTO> {
        return when (val response =
            apiClient.safeApiCall { movieServiceApi.getMovieNowPlaying(page) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun getMoviePopular(page: Int): Either<Exception, MoviesDTO> {
        return when (val response = apiClient.safeApiCall { movieServiceApi.getMoviePopular(page) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun getMovieDetail(movieId: Int): Either<Exception, MovieDetailDTO> {
        return when (val response =
            apiClient.safeApiCall { movieServiceApi.getMovieDetail(movieId) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }
}