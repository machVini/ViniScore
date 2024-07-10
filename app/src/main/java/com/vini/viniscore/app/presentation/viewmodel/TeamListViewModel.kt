package com.vini.viniscore.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.viniscore.app.data.model.Team
import com.vini.viniscore.app.data.repository.FootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamListViewModel @Inject constructor(
    private val repository: FootballRepository
) : ViewModel() {

    private val _teams = MutableStateFlow<List<Team>>(emptyList())
    val teams: StateFlow<List<Team>> get() = _teams

    fun getTeamsDetails(leagueID: Int, season: Int) {
        viewModelScope.launch {
            val result = repository.getTeams(leagueID, season).map { it.team }
            _teams.value = result
        }
    }
}
