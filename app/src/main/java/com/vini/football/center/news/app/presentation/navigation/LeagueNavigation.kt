package com.vini.football.center.news.app.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.vini.football.center.news.app.presentation.screens.TeamListScreen
import com.vini.football.center.news.app.presentation.screens.LeagueListScreen
import com.vini.football.center.news.app.presentation.viewmodel.TeamListViewModel
import com.vini.football.center.news.app.presentation.viewmodel.LeagueListViewModel
import kotlinx.serialization.Serializable

@Serializable
object LeagueListRoute

@Serializable
data class TeamListRoute(
    val leagueID: Int,
    val season: Int
)

fun NavGraphBuilder.leagueListScreen(
    onNavigateToDetailScreen: (Int, Int) -> Unit
){
    composable<LeagueListRoute> {
        val viewModel: LeagueListViewModel = hiltViewModel()
        val leagues by viewModel.leagues.collectAsState()
        LeagueListScreen(
            leagues = leagues,
            onItemClick = { id, season ->
                onNavigateToDetailScreen(id, season)
            }
        )
    }
}

fun NavGraphBuilder.teamListScreen(){
    composable<TeamListRoute> { backStackEntry ->
        val viewModel: TeamListViewModel = hiltViewModel()
        val details by viewModel.teams.collectAsState()
        val teamListRoute: TeamListRoute = backStackEntry.toRoute()
        viewModel.getTeamsDetails(
            leagueID = teamListRoute.leagueID,
            season = teamListRoute.season,
        )
        TeamListScreen(details)
    }
}

fun NavHostController.navigateToLeagueDetailsScreen(leagueID: Int, season: Int) {
    navigate(TeamListRoute(leagueID, season))
}