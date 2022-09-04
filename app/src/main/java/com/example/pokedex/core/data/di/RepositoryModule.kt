package com.example.pokedex.core.data.di

import com.example.pokedex.core.data.repository.DetailRepository
import com.example.pokedex.core.data.repository.DetailRepositoryImpl
import com.example.pokedex.core.data.repository.MainRepository
import com.example.pokedex.core.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsMainRepository(
        mainRepository: MainRepositoryImpl
    ): MainRepository

    @Binds
    fun bindsDetailRepository(
        detailRepository: DetailRepositoryImpl
    ): DetailRepository
}