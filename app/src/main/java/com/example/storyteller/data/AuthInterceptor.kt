package com.example.storyteller.data

import com.example.storyteller.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val url = originalRequest
            .url()
            .newBuilder()
            .addQueryParameter("ClientPlatform", BuildConfig.CLIENT_PLATFORM)
            .addQueryParameter("ClientVersion", BuildConfig.CLIENT_VERSION)
            .addQueryParameter("x-storyteller-api-key", apiKey)
            .build()

        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}