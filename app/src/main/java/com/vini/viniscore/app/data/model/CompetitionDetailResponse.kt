package com.vini.viniscore.app.data.model

data class CompetitionDetailResponse(
    val filters: Filters,
    val area: Area,
    val competition: Competition,
    val season: Season,
    val standings: List<Standing>
)

data class Filters(
    val season: String
)

data class Season(
    val id: Int,
    val startDate: String,
    val endDate: String,
    val currentMatchday: Int,
    val winner: Any?
)

data class Standing(
    val stage: String,
    val type: String,
    val group: Any?,
    val table: List<Table>
)

data class Table(
    val position: Int,
    val team: Team,
    val playedGames: Int,
    val form: Any?,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val points: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int
)

data class Team(
    val id: Int,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String
)
