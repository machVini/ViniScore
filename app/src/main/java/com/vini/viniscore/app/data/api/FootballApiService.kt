package com.vini.viniscore.app.data.api

import com.vini.viniscore.app.data.model.CompetitionDetailResponse
import com.vini.viniscore.app.data.model.CompetitionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FootballApiService {
    @GET("competitions")
    suspend fun getCompetitions(): CompetitionResponse

    @GET("competitions/{id}/standings")
    suspend fun getCompetitionDetails(
        @Path("id") competitionId: Int
    ): CompetitionDetailResponse
}