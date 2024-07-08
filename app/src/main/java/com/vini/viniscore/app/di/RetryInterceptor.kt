package com.vini.viniscore.app.di

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RetryInterceptor(private val maxRetry: Int) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response
        var tryCount = 0
        var request = chain.request()

        while (true) {
            try {
                response = chain.proceed(request)
                if (!response.isSuccessful && response.code == 429 && tryCount < maxRetry) {
                    tryCount++
                    response.close()
                    Thread.sleep(1000L * tryCount)
                } else {
                    return response
                }
            } catch (e: IOException) {
                if (tryCount >= maxRetry) {
                    throw e
                }
                tryCount++
                Thread.sleep(1000L * tryCount)
            }
        }
    }
}
