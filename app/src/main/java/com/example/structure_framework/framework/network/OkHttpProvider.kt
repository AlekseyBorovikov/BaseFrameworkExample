package com.example.structure_framework.framework.network

import com.google.android.datatransport.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

/**
 * Creates and configures the OkHttpClient
 * */
object OkHttpProvider {

    private const val TIMEOUT = 40L

    /**
     * Creates OkHttp client
     * @param interceptors: common interceptors
     * @param networkInterceptors: network interceptors
     *
     * @return [OkHttpClient]
     * */
    fun createOkHttpClient(
        interceptors: List<Interceptor>? = null,
        networkInterceptors: List<Interceptor>? = null
    ): OkHttpClient {

        // set timeouts
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            // set HttpLoggingInterceptor
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(httpLoggingInterceptor)
        }

        //set common interceptors
        interceptors?.forEach {
            builder.addInterceptor(it)
        }

        //set network interceptors
        networkInterceptors?.forEach {
            builder.addNetworkInterceptor(it)
        }

        return builder.build()
    }
}

// Sample:
// private const val CACHE_SIZE = 200 * 1024 // 200Kb
//
// @Singleton
// @Provides
// fun provideLruCache(): LruCache<String, CacheResponse> = LruCache(CACHE_SIZE)
//
// @Singleton
// @Provides
// fun provideOkHttpClient(
//     lruCache: LruCache<String, CacheResponse>
// ): OkHttpClient = OkHttpProvider.createOkHttpClient(interceptors=listOf(CacheInterceptor(lruCache)))