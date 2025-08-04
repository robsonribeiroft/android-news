package br.com.robsonribeiroft.androidnews.component.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.extensions.empty
import br.com.robsonribeiroft.androidnews.extensions.withColor
import br.com.robsonribeiroft.androidnews.model.News
import coil3.compose.AsyncImage

@Composable
fun NewsItemComponent(
    news: News,
    modifier: Modifier = Modifier,
    onClick: (News)-> Unit
) {
    Surface(
        modifier = modifier
            .clickable {
                onClick(news)
            }
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = dimensionResource(R.dimen.single)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(dimensionResource(R.dimen.large)),
            verticalArrangement = Arrangement.spacedBy(
                space = dimensionResource(R.dimen.regular),
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = news.label,
                style = MaterialTheme.typography.labelMedium.withColor { onSurfaceVariant }
            )
            Text(
                text = news.title,
                style = MaterialTheme.typography.titleLarge.withColor { onSurface }
            )
            Text(
                text = news.summary,
                style = MaterialTheme.typography.bodyMedium.withColor { onSurfaceVariant }
            )

            AsyncImage(
                modifier = Modifier
                    .height(dimensionResource(R.dimen.thumbnail_item_list))
                    .padding(vertical = dimensionResource(R.dimen.regular)),
                model = news.thumbnailUrl,
                contentScale = ContentScale.FillBounds,
                contentDescription = String.empty
            )

            Text(
                text = news.metadata,
                style = MaterialTheme.typography.labelMedium.withColor { onSurfaceVariant }
            )
        }
    }
}
