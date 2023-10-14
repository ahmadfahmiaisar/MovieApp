package com.santridev.newmovieapp.base.di

import com.santridev.newmovieapp.base.dispatcher.CoroutineDispatcherProvider
import com.santridev.newmovieapp.base.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface CoroutineDispatcherModule {
    @Binds
    fun bindDispatcher(dispatcherProvider: CoroutineDispatcherProvider): DispatcherProvider
}