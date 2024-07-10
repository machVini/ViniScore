package com.vini.football.center.news.app.di

import com.vini.football.center.news.BuildConfig.API_KEY
import com.vini.football.center.news.BuildConfig.BASE_URL
import com.vini.football.center.news.app.data.api.FootballApiService
import com.vini.football.center.news.app.data.repository.FootballRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val MAX_RETRY = 3

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiKey(): String = API_KEY

    @Provides
    @Singleton
    fun provideMaxRetry(): Int = MAX_RETRY

    @Provides
    @Singleton
    fun provideAuthInterceptor(apiKey: String): AuthInterceptor {
        return AuthInterceptor(apiKey)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideFootballApiService(retrofit: Retrofit): FootballApiService {
        return retrofit.create(FootballApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideFootballRepository(apiService: FootballApiService): FootballRepository {
        return FootballRepository(apiService)
    }
}
