package com.santridev.newmovieapp.data.local.di

import com.santridev.newmovieapp.data.local.dao.FavoriteMovieDAO
import com.santridev.newmovieapp.data.local.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object DaoModule {

    @Provides
    fun provideFavoriteMovieDao(database: MovieDatabase): FavoriteMovieDAO {
        return database.movieDao()
    }
}