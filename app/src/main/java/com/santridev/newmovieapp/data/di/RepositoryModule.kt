package com.santridev.newmovieapp.data.di

import com.santridev.newmovieapp.data.repository.MovieRepositoryImpl
import com.santridev.newmovieapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindCustomerRepository(repository: MovieRepositoryImpl): MovieRepository
}