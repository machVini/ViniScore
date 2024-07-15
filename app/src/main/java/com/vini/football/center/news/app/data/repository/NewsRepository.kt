package com.vini.football.center.news.app.data.repository

import com.vini.football.center.news.app.data.api.NewsApiService
import com.vini.football.center.news.app.data.model.NewsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: NewsApiService
) {
    suspend fun getTopHeadlines(category: String, pageSize: Int, page: Int): NewsResponse {
        return apiService.getTopHeadlines(category, pageSize, page)
    }

    suspend fun searchNews(query: String, pageSize: Int, page: Int): NewsResponse {
        return apiService.searchNews(query, pageSize, page)
    }

    suspend fun getNewsSources(category: String?, language: String?, country: String?): NewsResponse {
        return apiService.getNewsSources(category, language, country)
    }

    suspend fun getNewsForTeam(teamName: String): NewsResponse {
        return apiService.getNewsForTeam(teamName)
    }
}