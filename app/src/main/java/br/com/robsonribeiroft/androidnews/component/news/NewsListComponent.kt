package br.com.robsonribeiroft.androidnews.component.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.component.ErrorComponent
import br.com.robsonribeiroft.androidnews.extensions.colorTheme
import br.com.robsonribeiroft.androidnews.model.News
import br.com.robsonribeiroft.androidnews.ui.theme.BackgroundDark
import br.com.robsonribeiroft.androidnews.ui.theme.BackgroundLight
import br.com.robsonribeiroft.androidnews.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListComponent(
    modifier: Modifier,
    lazyFeedNews: LazyPagingItems<News>,
    onItemClick: (News)-> Unit
) {

    val listState = rememberLazyListState()
    val isRefreshing = lazyFeedNews.loadState.refresh is LoadState.Loading

    Box(
        modifier
            .fillMaxSize()
            .background(color = colorTheme(BackgroundLight, BackgroundDark))
    ) {
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            state = rememberPullToRefreshState(),
            onRefresh = {
                lazyFeedNews.refresh()
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                state = listState,
                verticalArrangement = Arrangement.spacedBy(
                    space = dimensionResource(R.dimen.large),
                    alignment = Alignment.CenterVertically
                )
            ) {
                items(
                    lazyFeedNews.itemCount,
                    key = lazyFeedNews.itemKey { it.id }
                ) { index ->
                    val news = lazyFeedNews[index]
                    if (news != null) {
                        NewsItemComponent(news, onClick = onItemClick)
                    }
                }

                lazyFeedNews.loadState.apply {
                    when {
                        refresh is LoadState.Loading || append is LoadState.Loading -> {
                            item {
                                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                    CircularProgressIndicator(color = Primary, modifier = Modifier.align(Alignment.Center))
                                }
                            }
                        }
                        refresh is LoadState.Error -> {
                            item {
                                ErrorComponent(
                                    errorMessageId = R.string.error_network_unavailable,
                                    icon = Icons.Default.WifiOff
                                )
                            }
                        }
                        append is LoadState.Error -> {
                            item {
                                ErrorComponent(
                                    errorMessageId = R.string.error_load_more_items
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
