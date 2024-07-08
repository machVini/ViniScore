package com.vini.viniscore.app.data.model

data class CompetitionResponse(
    val competitions: List<Competition>
)

data class Competition(
    val id: Int,
    val name: String,
    val area: Area,
    val emblem: String,
    val type: CompetitionType,
)

data class Area(
    val id: Int,
    val name: String,
    val flag: String,
)

enum class CompetitionType {
    CUP, LEAGUE
}
