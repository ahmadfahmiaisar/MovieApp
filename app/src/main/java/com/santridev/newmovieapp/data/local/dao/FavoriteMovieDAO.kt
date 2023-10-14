package com.santridev.newmovieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santridev.newmovieapp.data.dto.MovieItemDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMovieDAO {
    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteMovie(): Flow<List<MovieItemDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(movie: MovieItemDTO)

    @Delete
    suspend fun deleteFavoriteMovie(movie: MovieItemDTO)
}
