package com.vini.viniscore.app.data.repository

import android.util.Log
import com.vini.viniscore.app.data.api.FootballApiService
import com.vini.viniscore.app.data.model.TeamsResponse
import com.vini.viniscore.app.data.model.LeagueResponse
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