package com.example.structure_framework.framework.data

import okhttp3.Request
import okhttp3.Headers
import okhttp3.Protocol

/**
 * The stored response.
 * Instance of the [Response] that stores the necessary data in the cache.
 *
 * Required, because not all response parameters can be saved
 * @see [com.example.structure_framework.framework.annotations.Cacheable]
 * */
data class CacheResponse(
    val request: Request,
    val headers: Headers,
    val protocol: Protocol,
    val code: Int,
    val message: String,
    val body: ByteArray?,
    val receivedResponseAtMillis: Long,
    val sentRequestAtMillis: Long
): java.io.Serializable