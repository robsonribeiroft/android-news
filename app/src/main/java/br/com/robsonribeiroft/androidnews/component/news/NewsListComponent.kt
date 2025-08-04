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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.extensions.colorTheme
import br.com.robsonribeiroft.androidnews.model.News
import br.com.robsonribeiroft.androidnews.ui.theme.BackgroundDark
import br.com.robsonribeiroft.androidnews.ui.theme.BackgroundLight
import br.com.robsonribeiroft.androidnews.ui.theme.Primary
import coil3.util.Logger

@Composable
fun NewsListComponent(
    modifier: Modifier,
    feedNews: List<News>,
    onItemClick: (News)-> Unit
) {

    val listState = rememberLazyListState()

    Box(
        modifier
            .fillMaxSize()
            .background(color = colorTheme(BackgroundLight, BackgroundDark))
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(
                space = dimensionResource(R.dimen.large),
                alignment = Alignment.CenterVertically
            )
        ) {
            items(feedNews) { news ->
                NewsItemComponent(news, onClick = onItemClick)
            }
        }
    }
}
