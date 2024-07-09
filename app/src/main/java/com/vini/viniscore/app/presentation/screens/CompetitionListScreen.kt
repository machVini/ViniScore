package com.vini.viniscore.app.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.vini.viniscore.app.data.model.Competition
import com.vini.viniscore.app.data.model.Table
import com.vini.viniscore.app.presentation.viewmodel.CompetitionDetailViewModel
import com.vini.viniscore.app.presentation.viewmodel.CompetitionListViewModel


@Composable
fun CompetitionListScreen(
    viewModel: CompetitionListViewModel,
    navController: NavController,
) {
    val competitions by viewModel.competitions.collectAsState()
    Log.d("teste", "competitions: $competitions")

    Column {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(competitions) { competition ->
                CompetitionItem(competition, navController)
            }
        }
    }
}

@Composable
fun CompetitionItem(
    competition: Competition,
    navController: NavController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                navController.navigate("competitionDetailScreen/${competition.id}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(competition.emblem),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 16.dp)
        )
        Text(
            text = competition.name,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun CompetitionDetailScreen(
    competitionId: Int,
    viewModel: CompetitionDetailViewModel
) {
    val competitionDetail by viewModel.competitionDetail.collectAsState()

    viewModel.fetchCompetitionDetails(competitionId)

    competitionDetail?.let { detail ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(detail.standings.first().table) { table ->
                StandingItem(table)
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun StandingItem(table: Table) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(start = 2.dp, end = 8.dp),
            text = table.position.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Image(
            painter = rememberAsyncImagePainter(table.team.crest),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .padding(end = 4.dp)
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = table.team.shortName,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = table.points.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = table.goalsFor.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = table.goalsAgainst.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier.padding(4.dp),
            text = table.goalDifference.toString(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
