package com.vini.football.center.news.app.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vini.football.center.news.app.data.model.Article

@Composable
fun TeamNewsScreen(news: List<Article>) {
    LazyColumn {
        items(news) { newsItem ->
            NewsItem(news = newsItem)
        }
    }
}

@Composable
fun NewsItem(news: Article) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = news.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = news.content.orEmpty())
    }
}