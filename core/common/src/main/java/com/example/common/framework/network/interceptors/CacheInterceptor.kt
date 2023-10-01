package com.example.common.framework.network.interceptors

import android.util.LruCache
import com.example.common.framework.annotations.Cacheable
import com.example.common.framework.cache.toCacheResponse
import com.example.common.framework.cache.toResponse
import com.example.common.framework.data.CacheResponse
import com.example.common.framework.extension.HOUR_IN_MILLIS
import com.example.common.framework.extension.MINUTE_IN_MILLIS
import com.example.common.framework.extension.SECOND_IN_MILLIS
import com.example.common.framework.extension.dayOfMonth
import com.example.common.framework.extension.hour
import com.example.common.framework.extension.minute
import com.example.common.framework.extension.month
import com.example.common.framework.extension.second
import com.example.common.framework.extension.utc
import com.example.common.framework.extension.utcToCalendar
import com.example.common.framework.extension.year
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Invocation
import timber.log.Timber
import java.util.Calendar

/**
 * Intercepts requests with the Cacheable annotation.
 * If the caching time has not yet expired, then returns the data from the cache,
 * otherwise it executes the request.
 * @param lruCache - link to the storage for writing, reading, and syncing.
 * @see [com.example.app.libraries.framework.annotations.Cacheable]
 * */
class CacheInterceptor(private val lruCache: LruCache<String, CacheResponse>): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val invocation: Invocation? = request.tag(Invocation::class.java)
        if (invocation != null) {
            // check for @Cacheable annotation
            val annotation: Cacheable? = invocation.method().getAnnotation(Cacheable::class.java)
            if (annotation?.annotationClass?.simpleName.equals("Cacheable")) {
                val key = invocation.getCacheKey()
                Timber.tag(TAG).d("Cacheable method -> $key")
                synchronized(lruCache) {
                    // convert time parameters to milliseconds
                    val maxAge = annotation!!.hours * HOUR_IN_MILLIS +
                            annotation!!.minutes * MINUTE_IN_MILLIS +
                            annotation!!.seconds * SECOND_IN_MILLIS
                    // if the request is not cached or the storage time has expired,
                    // executes the request and saves the result
                    return responseFromCache(key,maxAge) ?: chain.proceedAndCache(key)
                }
            }
        }
        return chain.proceed(request)
    }

    /**
     * Returns a response if it's stored in the cache and the storage time has not expired
     * @param key: unique identifier of the cached method
     * @param maxAge: cache storage time in milliseconds (so far only hours, minutes and seconds are involved)
     * @return [Response] from cache or null
     * */
    private fun responseFromCache(key: String, maxAge: Long): Response? {
        lruCache.get(key)?.let { cacheResponse->
            // current time
            val now = Calendar.getInstance().utc
            // time of saved request
            val resp = cacheResponse.receivedResponseAtMillis.utcToCalendar()
            // converting hours, minutes and seconds to milliseconds for comparison
            val nowMillis = now.hour * HOUR_IN_MILLIS + now.minute* MINUTE_IN_MILLIS + now.second* SECOND_IN_MILLIS
            val respMillis = resp.hour * HOUR_IN_MILLIS + resp.minute* MINUTE_IN_MILLIS + resp.second* SECOND_IN_MILLIS
            // If the request is in the cache,
            // check the storage time
            if(
                now.year == resp.year &&
                now.month == resp.month &&
                now.dayOfMonth == resp.dayOfMonth
            ) {
                if (nowMillis - respMillis <= maxAge) {
                    Timber.tag(TAG).d(
                "Response from cache. \nmax-age: %d\nreceivedResponseAtMillis %s",
                        maxAge, cacheResponse.receivedResponseAtMillis.utcToCalendar()
                    )
                    return cacheResponse.toResponse()
                }
            }
        }
        return null
    }

    /**
     * Executes the request and saves the cache in response
     * @param key: unique identifier of the cached method
     * @return [Response] from the server
     * */
    private fun Interceptor.Chain.proceedAndCache(key: String): Response {
        // Execute request
        var newResponse = this.proceed(this.request())
        // check if it's successful
        if(newResponse.isSuccessful) {
            // convert to stored data
            val cachedResponse = newResponse.toCacheResponse()
            // put in cache
            lruCache.put(key, cachedResponse)
            // put back the request body after conversion
            newResponse = newResponse.newBuilder()
                .body(cachedResponse.body?.let { it.toResponseBody("application/json".toMediaTypeOrNull()) })
                .build()
        }
        return newResponse
    }

    /**
     * Returns a unique method name
     * */
    private fun Invocation.getCacheKey() = "${this.method().name}#${this.arguments().hashCode()}"

    companion object {
        private const val TAG = "CacheLog"
    }
}