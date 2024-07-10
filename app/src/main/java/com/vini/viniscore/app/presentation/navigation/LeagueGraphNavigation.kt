package com.vini.viniscore.app.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable
object LeagueGraph

fun NavGraphBuilder.leagueGraph(
    onNavigateToDetailScreen: (Int, Int) -> Unit
) {
    navigation<LeagueGraph>(
        startDestination = LeagueListRoute
    ) {
        leagueListScreen(
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )
        teamListScreen()
    }
}