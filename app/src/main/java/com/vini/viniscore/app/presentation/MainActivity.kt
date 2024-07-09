package com.vini.viniscore.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vini.viniscore.app.presentation.navigation.CompetitionDetailRoute
import com.vini.viniscore.app.presentation.navigation.CompetitionsRoute
import com.vini.viniscore.app.presentation.navigation.competitionDetailScreen
import com.vini.viniscore.app.presentation.navigation.competitionListScreen
import com.vini.viniscore.app.presentation.navigation.navigateToDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = CompetitionsRoute) {
                competitionListScreen(
                    onNavigateToDetailScreen = {
                        navController.navigateToDetailScreen(it)
                    }
                )
                competitionDetailScreen()
            }
        }
    }
}