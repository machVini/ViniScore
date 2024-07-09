package com.vini.viniscore.app.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.vini.viniscore.app.presentation.screens.CompetitionDetailScreen
import com.vini.viniscore.app.presentation.screens.CompetitionListScreen
import com.vini.viniscore.app.presentation.viewmodel.CompetitionDetailViewModel
import com.vini.viniscore.app.presentation.viewmodel.CompetitionListViewModel
import kotlinx.serialization.Serializable

@Serializable
object CompetitionsRoute

@Serializable
data class CompetitionDetailRoute(val competitionId: Int)

fun NavGraphBuilder.competitionListScreen(
    onNavigateToDetailScreen: (Int) -> Unit
){
    composable<CompetitionsRoute> {
        val viewModel: CompetitionListViewModel = hiltViewModel()
        val competitions by viewModel.competitions.collectAsState()
        CompetitionListScreen(
            competitions = competitions,
            onItemClick = { onNavigateToDetailScreen(it) }
        )
    }
}

fun NavGraphBuilder.competitionDetailScreen(){
    composable<CompetitionDetailRoute> { backStackEntry ->
        val viewModel: CompetitionDetailViewModel = hiltViewModel()
        val detail by viewModel.competitionDetail.collectAsState()
        val competitionDetailRoute: CompetitionDetailRoute = backStackEntry.toRoute()
        viewModel.fetchCompetitionDetails(competitionDetailRoute.competitionId)
        CompetitionDetailScreen(detail)
    }
}

fun NavHostController.navigateToDetailScreen(competitionId: Int) {
    navigate(CompetitionDetailRoute(competitionId))
}