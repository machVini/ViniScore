package com.vini.viniscore.app.di

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .header("X-Auth-Token", apiKey)
            .build()

        return chain.proceed(request)
    }
}
