package com.vini.football.center.news.app.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.vini.football.center.news.app.presentation.screens.TeamsScreen
import com.vini.football.center.news.app.presentation.screens.LeaguesScreen
import com.vini.football.center.news.app.presentation.screens.TeamNewsScreen
import com.vini.football.center.news.app.presentation.viewmodel.TeamListViewModel
import com.vini.football.center.news.app.presentation.viewmodel.LeagueListViewModel
import com.vini.football.center.news.app.presentation.viewmodel.TeamNewsViewModel
import kotlinx.serialization.Serializable

@Serializable
object LeagueListRoute

@Serializable
data class TeamListRoute(
    val leagueID: Int,
    val season: Int,
)

@Serializable
data class TeamNewsRoute(
    val name: String,
)

fun NavGraphBuilder.leagueListScreen(
    onNavigateToDetailScreen: (Int, Int) -> Unit
){
    composable<LeagueListRoute> {
        val viewModel: LeagueListViewModel = hiltViewModel()
        val leagues by viewModel.leagues.collectAsState()
        LeaguesScreen(
            leagues = leagues,
            onItemClick = { id, season ->
                onNavigateToDetailScreen(id, season)
            }
        )
    }
}

fun NavGraphBuilder.teamListScreen(
    onNavigateToTeamNewsScreen: (String) -> Unit
){
    composable<TeamListRoute> { backStackEntry ->
        val viewModel: TeamListViewModel = hiltViewModel()
        val details by viewModel.teams.collectAsState()
        val teamListRoute: TeamListRoute = backStackEntry.toRoute()
        viewModel.getTeamsDetails(
            leagueID = teamListRoute.leagueID,
            season = teamListRoute.season,
        )
        TeamsScreen(
            teams = details,
            onItemClick = { teamName ->
                onNavigateToTeamNewsScreen(teamName)
            }
        )
    }
}

fun NavGraphBuilder.teamNewsScreen(){
    composable<TeamNewsRoute> { backStackEntry ->
        val teamNewsRoute: TeamNewsRoute = backStackEntry.toRoute()
        val viewModel: TeamNewsViewModel = hiltViewModel()
        val news by viewModel.newsState.collectAsState(emptyList())
        viewModel.getNewsForTeam(
            teamName = teamNewsRoute.name
        )
        TeamNewsScreen(news = news)
    }
}