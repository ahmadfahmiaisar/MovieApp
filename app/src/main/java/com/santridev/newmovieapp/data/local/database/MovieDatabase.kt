package com.santridev.newmovieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santridev.newmovieapp.data.dto.MovieItemDTO
import com.santridev.newmovieapp.data.local.dao.FavoriteMovieDAO

@Database(
    entities = [
        MovieItemDTO::class
    ],
    version = 1
)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): FavoriteMovieDAO
}