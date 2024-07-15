package com.vini.football.center.news.app.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable
object LeagueGraph

fun NavGraphBuilder.leagueGraph(
    onNavigateToDetailScreen: (Int, Int) -> Unit,
    onNavigateToTeamNewsScreen: (String) -> Unit,
) {
    navigation<LeagueGraph>(
        startDestination = LeagueListRoute
    ) {
        leagueListScreen(
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )
        teamListScreen(
            onNavigateToTeamNewsScreen = onNavigateToTeamNewsScreen
        )
        teamNewsScreen()
    }
}