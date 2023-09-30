package com.example.common.framework.cache

import com.example.common.framework.data.CacheResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Convert cachable [CacheResponse] to [okhttp3.Response]
 * @return [okhttp3.Response]
 * @see [com.example.app.framework.annotations.Cacheable]
 * @see [com.example.app.framework.network.interceptors.CacheInterceptor]
 * */
fun CacheResponse.toResponse(): Response = Response.Builder()
    .message(message)
    .protocol(protocol)
    .code(code)
    .headers(headers)
    .body(body?.toResponseBody("application/json; charset=UTF-8".toMediaTypeOrNull()))
    .receivedResponseAtMillis(receivedResponseAtMillis)
    .sentRequestAtMillis(sentRequestAtMillis)
    .request(request)
    .build()

/**
 * Convert [okhttp3.Response] to cachable [CacheResponse]
 * @return [CacheResponse]
 * @see [com.example.app.framework.annotations]
 * @see [com.example.app.framework.network.interceptors.CacheInterceptor]
 * */
fun Response.toCacheResponse() = CacheResponse(
    request=request,
    headers= headers,
    protocol= protocol,
    code= code,
    message= message,
    body= body?.bytes(),
    receivedResponseAtMillis= receivedResponseAtMillis,
    sentRequestAtMillis= sentRequestAtMillis,
)