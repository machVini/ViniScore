package com.vini.football.center.news.app.data.api

import com.vini.football.center.news.app.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") page: Int = 1
    ): NewsResponse

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") page: Int = 1
    ): NewsResponse

    @GET("v2/sources")
    suspend fun getNewsSources(
        @Query("category") category: String? = null,
        @Query("language") language: String? = "pt",
        @Query("country") country: String? = null
    ): NewsResponse

    @GET("v2/everything")
    suspend fun getNewsForTeam(
        @Query("q") teamName: String,
        @Query("language") language: String? = "pt",
        @Query("pageSize") pageSize: Int = 20
    ): NewsResponse
}
