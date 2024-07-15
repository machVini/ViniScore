package com.vini.football.center.news.app.di

import com.vini.football.center.news.app.data.api.FootballApiService
import com.vini.football.center.news.app.data.api.NewsApiService
import com.vini.football.center.news.app.data.repository.FootballRepository
import com.vini.football.center.news.app.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFootballRepository(@Football apiService: FootballApiService): FootballRepository {
        return FootballRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(@News apiService: NewsApiService): NewsRepository {
        return NewsRepository(apiService)
    }
}
