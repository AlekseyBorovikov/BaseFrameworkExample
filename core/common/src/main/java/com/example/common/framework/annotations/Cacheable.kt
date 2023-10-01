package com.example.common.framework.annotations

/**
 * Annotation for marking requests that need to be stored in the cache.
 * @param hours: maximum number of storage hours
 * @param minutes: maximum number of storage minutes
 * @param seconds: maximum number of storage seconds
 *
 * @see [com.example.app.framework.network.interceptors.CacheInterceptor]
 * */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Cacheable(
    val hours: Long = 0,
    val minutes: Long = 0,
    val seconds: Long = 0,
)

// Sample:
// @Cacheable(hours = 2)
// @GET(GET_USER_DATA)
// suspend fun getUserData(
//     @Header("Authorization") token: String,
// ): Response<User>
