package com.example.remote.di

import android.util.LruCache
import com.example.common.framework.data.CacheResponse
import com.example.common.framework.network.OkHttpProvider
import com.example.common.framework.network.RetrofitManager
import com.example.common.framework.network.interceptors.CacheInterceptor
import com.example.remote.api.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

private const val CACHE_SIZE = 200 * 1024 // 200Kb
private const val BASE_URL = ""

/**
 * DI module for provide api instances.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * Provides [LruCache] for cached responses
     * @return - [LruCache]
     * */
    @Singleton
    @Provides
    fun provideLruCache(): LruCache<String, CacheResponse> = LruCache(CACHE_SIZE)

    /**
     * Provide [Retrofit] instance for program.
     */
    @Singleton
    @Provides
    fun provideRetrofit(
        lruCache: LruCache<String, CacheResponse>,
    ): Retrofit = RetrofitManager.getRetrofit(
        okHttpClient = OkHttpProvider.createOkHttpClient(interceptors = listOf(CacheInterceptor(lruCache))),
        baseUrl = BASE_URL,
    )

    /**
     * Provides [CharacterService] instance
     * @param retrofit: instance for creating [CharacterService]
     * @return [CharacterService]
     */
    @Singleton
    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)
}