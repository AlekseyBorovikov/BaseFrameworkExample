package com.example.common.framework.network

import com.example.common.framework.extension.DATE_AND_TIME_TO_MILLIS_FORMAT
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Creates and configures the Retrofit object
 * */
object RetrofitManager {

    /**
     * Get configured retrofit instance
     * @return [Retrofit]
     * */
    fun getRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {

        val gson = GsonBuilder().setLenient().setDateFormat(DATE_AND_TIME_TO_MILLIS_FORMAT).create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}