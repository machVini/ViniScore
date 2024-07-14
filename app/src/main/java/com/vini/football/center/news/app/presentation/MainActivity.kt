package com.vini.football.center.news.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vini.football.center.news.app.presentation.navigation.LeagueListRoute
import com.vini.football.center.news.app.presentation.navigation.TeamListRoute
import com.vini.football.center.news.app.presentation.navigation.teamListScreen
import com.vini.football.center.news.app.presentation.navigation.leagueListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = LeagueListRoute) {
                leagueListScreen(
                    onNavigateToDetailScreen = { id, season ->
                        navController.navigate(
                            TeamListRoute(
                                leagueID = id,
                                season = season,
                            )
                        )
                    }
                )
                teamListScreen()
            }
        }
    }
}