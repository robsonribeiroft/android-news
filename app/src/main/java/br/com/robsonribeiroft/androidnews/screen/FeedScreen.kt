package br.com.robsonribeiroft.androidnews.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import br.com.robsonribeiroft.androidnews.AppViewModel
import br.com.robsonribeiroft.androidnews.component.news.NewsListComponent
import br.com.robsonribeiroft.androidnews.component.toolbar.TopBarComponent
import br.com.robsonribeiroft.androidnews.model.News
import org.koin.androidx.compose.koinViewModel

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = koinViewModel(),
    navigateToNews: (News)-> Unit
) {

    val feed by viewModel.feed.collectAsState()

    Column {
        TopBarComponent()
        NewsListComponent(
            modifier = modifier.fillMaxSize(),
            feedNews = feed,
            onItemClick = navigateToNews
        )
    }
}