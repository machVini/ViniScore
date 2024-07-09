package com.vini.viniscore.app.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import kotlinx.serialization.Serializable

@Serializable
object CompetitionGraph

fun NavGraphBuilder.competitionGraph(
    onNavigateToDetailScreen: (Int) -> Unit
) {
    navigation<CompetitionGraph>(
        startDestination = CompetitionsRoute
    ) {
        competitionListScreen(
            onNavigateToDetailScreen = onNavigateToDetailScreen
        )
        competitionDetailScreen()
    }
}