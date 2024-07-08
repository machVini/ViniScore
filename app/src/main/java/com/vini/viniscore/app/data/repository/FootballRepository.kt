package com.vini.viniscore.app.data.repository

import com.vini.viniscore.app.data.api.FootballApiService
import com.vini.viniscore.app.data.model.CompetitionDetailResponse
import com.vini.viniscore.app.data.model.CompetitionResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FootballRepository @Inject constructor(
    private val apiService: FootballApiService
) {
    suspend fun getCompetitions(): CompetitionResponse {
        return apiService.getCompetitions()
    }

    suspend fun getCompetitionDetails(competitionId: Int): CompetitionDetailResponse {
        return apiService.getCompetitionDetails(competitionId)
    }
}