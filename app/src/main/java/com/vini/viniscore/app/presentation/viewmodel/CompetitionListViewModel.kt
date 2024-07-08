package com.vini.viniscore.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.viniscore.app.data.model.Competition
import com.vini.viniscore.app.data.repository.FootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompetitionListViewModel @Inject constructor(
    private val footballRepository: FootballRepository
) : ViewModel() {

    private val _competitions = MutableStateFlow<List<Competition>>(emptyList())
    val competitions: StateFlow<List<Competition>> get() = _competitions

    init {
        getCompetitions()
    }

    private fun getCompetitions() {
        viewModelScope.launch {
            val result = footballRepository.getCompetitions()
            _competitions.value = result.competitions
        }
    }

    fun getCompetitionById(competitionId: String): Competition {
        return _competitions.value.firstOrNull { it.id == competitionId.toInt() }
            ?: throw IllegalArgumentException("Competition not found")
    }
}