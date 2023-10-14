package com.santridev.newmovieapp.data.service

import com.santridev.newmovieapp.data.dto.MovieDetailDTO
import com.santridev.newmovieapp.data.dto.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieServiceApi {
    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(@Query("page") page: Int): Response<MoviesDTO>

    @GET("movie/popular")
    suspend fun getMoviePopular(@Query("page") page: Int): Response<MoviesDTO>

    @GET("movie/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): Response<MovieDetailDTO>
}
