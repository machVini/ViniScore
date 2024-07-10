package com.vini.viniscore.app.data.model

data class LeagueResponse(
    val league: League,
    val country: Country,
)

data class League(
    val id: Int,
    val name: String,
    val country: Country,
    val logo: String,
    val isFavorite: Boolean = false,
)

data class Country(
    val name: String,
    val code: String?,
    val flag: String,
)