package br.com.robsonribeiroft.androidnews.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.robsonribeiroft.androidnews.AppViewModel
import br.com.robsonribeiroft.androidnews.component.news.NewsListComponent
import br.com.robsonribeiroft.androidnews.model.News

@Composable
fun FeedTabContent(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    product: String,
    navigateToNews: (News)-> Unit
) {

    val lazyFeeNews = viewModel.getFeedNewsByProduct(product).collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.setProduct(product)
    }

    Column {
        NewsListComponent(
            modifier = modifier.fillMaxSize(),
            lazyFeedNews = lazyFeeNews,
            onItemClick = navigateToNews
        )
    }
}