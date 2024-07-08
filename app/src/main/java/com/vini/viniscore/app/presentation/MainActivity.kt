package com.vini.viniscore.app.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vini.viniscore.app.presentation.screens.CompetitionDetailScreen
import com.vini.viniscore.app.presentation.screens.CompetitionListScreen
import com.vini.viniscore.app.presentation.viewmodel.CompetitionDetailViewModel
import com.vini.viniscore.app.presentation.viewmodel.CompetitionListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val competitionListViewModel: CompetitionListViewModel by viewModels()
    private val competitionDetailViewModel: CompetitionDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "competitionListScreen") {
                Log.d("teste", "screen 1")
                composable("competitionListScreen") {
                    CompetitionListScreen(viewModel = competitionListViewModel, navController = navController)
                }
                composable("competitionDetailScreen/{competitionId}") { backStackEntry ->
                    val competitionId = backStackEntry.arguments?.getString("competitionId")
                    Log.d("teste", "competitionID: $competitionId")
                    CompetitionDetailScreen(competitionId?.toInt() ?: 0, viewModel = competitionDetailViewModel)
                }
            }
        }
    }
}