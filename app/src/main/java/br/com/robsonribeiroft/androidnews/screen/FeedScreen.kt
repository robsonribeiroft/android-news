package br.com.robsonribeiroft.androidnews.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.robsonribeiroft.androidnews.AppViewModel
import br.com.robsonribeiroft.androidnews.component.news.NewsListComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeedScreen(viewModel: AppViewModel = koinViewModel()) {

    val feed by viewModel.feed.collectAsState()

    NewsListComponent(
        modifier = Modifier.fillMaxSize(),
        feedNews = feed
    ) {
        println("")
    }
}