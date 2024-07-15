package com.vini.football.center.news.app.di

import android.util.Log
import com.vini.football.center.news.BuildConfig.FOOTBALL_API_KEY
import com.vini.football.center.news.BuildConfig.FOOTBALL_BASE_URL
import com.vini.football.center.news.BuildConfig.NEWS_API_KEY
import com.vini.football.center.news.BuildConfig.NEWS_BASE_URL
import com.vini.football.center.news.app.data.api.FootballApiService
import com.vini.football.center.news.app.data.api.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Football
    @Provides
    @Singleton
    fun provideFootballOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("x-rapidapi-key", FOOTBALL_API_KEY)
                    .addHeader("x-rapidapi-host", "v3.football.api-sports.io")
                    .build()
                Log.d("Interceptor", "URL: ${request.url}")
                Log.d("Interceptor", "Headers: ${request.headers}")
                chain.proceed(request)
            }.build()
    }

    @Football
    @Provides
    @Singleton
    fun provideFootballRetrofit(@Football okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(FOOTBALL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Football
    @Provides
    @Singleton
    fun provideFootballApiService(@Football retrofit: Retrofit): FootballApiService {
        return retrofit.create(FootballApiService::class.java)
    }

    @News
    @Provides
    @Singleton
    fun provideNewsOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl: HttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", NEWS_API_KEY)
                    .build()
                val request = original.newBuilder()
                    .url(url)
                    .build()
                Log.d("Interceptor", "URL: ${request.url}")
                chain.proceed(request)
            }.build()
    }

    @News
    @Provides
    @Singleton
    fun provideNewsRetrofit(@News okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @News
    @Provides
    @Singleton
    fun provideNewsApiService(@News retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }
}