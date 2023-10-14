package com.santridev.newmovieapp.data.di

import com.santridev.newmovieapp.data.service.MovieServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideCustomerService(retrofit: Retrofit): MovieServiceApi =
        retrofit.create(MovieServiceApi::class.java)
}