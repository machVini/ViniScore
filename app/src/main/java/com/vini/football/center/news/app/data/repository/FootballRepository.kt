package com.vini.football.center.news.app.data.repository

import com.vini.football.center.news.app.data.api.FootballApiService
import com.vini.football.center.news.app.data.model.TeamsResponse
import com.vini.football.center.news.app.data.model.LeagueResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FootballRepository @Inject constructor(
    private val apiService: FootballApiService
) {
    suspend fun getLeagues(): List<LeagueResponse> {
        return apiService.getLeagues().response
    }

    suspend fun getTeams(leagueID: Int, season: Int): List<TeamsResponse> {
        return apiService.getTeams(leagueID, season).response
    }
}