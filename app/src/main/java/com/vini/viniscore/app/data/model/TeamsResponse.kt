package com.vini.viniscore.app.data.model

data class TeamsResponse(
    val team: Team,
    val venue: Venue,
)

data class Team(
    val id: Int,
    val name: String,
    val code: String,
    val country: String,
    val founded: String,
    val national: Boolean,
    val logo: String,
    val isFavorite: Boolean = false
)

data class Venue(
    val id: Int,
    val name: String,
    val address: String,
    val city: String,
    val capacity: Int,
    val surface: String,
    val image: String,
)
