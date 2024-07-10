package com.vini.football.center.news.app.data.api

import com.vini.football.center.news.app.data.model.TeamsResponse
import com.vini.football.center.news.app.data.model.LeagueResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApiService {
    data class ApiResponse<T>(
        val response: T
    )
    @GET("leagues")
    suspend fun getLeagues(): ApiResponse<List<LeagueResponse>>

    @GET("teams")
    suspend fun getTeams(
        @Query("league") leagueID: Int,
        @Query("season") season: Int,
    ): ApiResponse<List<TeamsResponse>>
}