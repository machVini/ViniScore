package com.vini.viniscore.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vini.viniscore.app.presentation.navigation.LeagueListRoute
import com.vini.viniscore.app.presentation.navigation.teamListScreen
import com.vini.viniscore.app.presentation.navigation.leagueListScreen
import com.vini.viniscore.app.presentation.navigation.navigateToLeagueDetailsScreen
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
                        navController.navigateToLeagueDetailsScreen(id, season)
                    }
                )
                teamListScreen()
            }
        }
    }
}