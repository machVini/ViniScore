package com.vini.football.center.news.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.football.center.news.app.data.model.Article
import com.vini.football.center.news.app.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamNewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {
    private val _newsState = MutableStateFlow<List<Article>>(emptyList())
    val newsState: StateFlow<List<Article>> get() = _newsState

    fun getNewsForTeam(teamName: String) {
        viewModelScope.launch {
            val result = repository.getNewsForTeam(teamName)
            _newsState.value = result.articles
        }
    }
}