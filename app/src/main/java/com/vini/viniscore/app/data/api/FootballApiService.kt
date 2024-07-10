package com.vini.viniscore.app.data.api

import com.vini.viniscore.app.data.model.TeamsResponse
import com.vini.viniscore.app.data.model.LeagueResponse
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