package com.santridev.newmovieapp.data.source

import com.santridev.newmovieapp.data.dto.MovieItemDTO
import com.santridev.newmovieapp.data.local.database.MovieDatabase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class MovieLocalDataSource @Inject constructor(
    private val database: MovieDatabase
) {
    suspend fun getAllFavoriteMovie(): Flow<List<MovieItemDTO>> {
        return database.movieDao().getAllFavoriteMovie().distinctUntilChanged()
    }

    suspend fun addFavoriteMovie(entity: MovieItemDTO) {
        database.movieDao().addFavoriteMovie(entity)
    }

    suspend fun deleteFavoriteMovie(entity: MovieItemDTO) {
        database.movieDao().deleteFavoriteMovie(entity)
    }
}