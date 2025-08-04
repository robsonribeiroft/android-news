package br.com.robsonribeiroft.androidnews.component.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.model.News

@Composable
fun NewsListComponent(
    modifier: Modifier,
    feedNews: List<News>,
    onClick: (News)-> Unit
) {

    val listState = rememberLazyListState()

    Box(
        modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(
                space = dimensionResource(R.dimen.regular),
                alignment = Alignment.CenterVertically
            )
        ) {
            items(feedNews) { news ->
                NewsItemComponent(news, onClick = onClick)
            }
        }
    }
}
