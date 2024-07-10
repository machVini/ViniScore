package com.vini.football.center.news.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.football.center.news.app.data.model.League
import com.vini.football.center.news.app.data.repository.FootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LeagueListViewModel @Inject constructor(
    private val footballRepository: FootballRepository
) : ViewModel() {

    private val _leagues = MutableStateFlow<List<League>>(emptyList())
    val leagues: StateFlow<List<League>> get() = _leagues

    init {
        getLeagues()
    }

    private fun getLeagues() {
        viewModelScope.launch {
            val result = footballRepository.getLeagues()
            _leagues.value = result.map { it.league }
        }
    }

    fun getCompetitionById(competitionId: Int): League {
        return _leagues.value.firstOrNull { it.id == competitionId }
            ?: throw IllegalArgumentException("Competition not found")
    }
}