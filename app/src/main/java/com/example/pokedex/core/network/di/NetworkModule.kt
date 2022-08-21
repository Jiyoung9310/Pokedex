package com.example.pokedex.core.network.di

import com.example.pokedex.core.network.PokemonNetworkDataSource
import com.example.pokedex.core.network.api.RetrofitPokemonNetworkApi
import com.example.pokedex.core.network.retrofit.RetrofitNetwork
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val POKE_HOST = "https://pokeapi.co/api/v2"

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsNiaNetwork(
        pokeNetwork: RetrofitNetwork
    ): PokemonNetworkDataSource

    companion object {

        @Singleton
        @Provides
        fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().apply {
                connectTimeout(200, TimeUnit.SECONDS)
                readTimeout(200, TimeUnit.SECONDS)
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }.build()


        @Singleton
        @Provides
        fun providesPokeRetrofit(client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(POKE_HOST)
                .addConverterFactory(
                    GsonConverterFactory.create(GsonBuilder().setLenient().create())
                )
                .client(client)
                .build()


        @Singleton
        @Provides
        fun providePokeService(retrofit: Retrofit): RetrofitPokemonNetworkApi = retrofit.create(
            RetrofitPokemonNetworkApi::class.java)

    }
}
