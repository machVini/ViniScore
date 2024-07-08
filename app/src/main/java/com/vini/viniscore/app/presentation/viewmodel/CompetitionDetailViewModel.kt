package com.vini.viniscore.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.viniscore.app.data.model.CompetitionDetailResponse
import com.vini.viniscore.app.data.repository.FootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompetitionDetailViewModel @Inject constructor(
    private val repository: FootballRepository
) : ViewModel() {

    private val _competitionDetail = MutableStateFlow<CompetitionDetailResponse?>(null)
    val competitionDetail: StateFlow<CompetitionDetailResponse?> get() = _competitionDetail

    fun fetchCompetitionDetails(competitionId: Int) {
        viewModelScope.launch {
            val response = repository.getCompetitionDetails(competitionId)
            _competitionDetail.value = response
        }
    }
}
