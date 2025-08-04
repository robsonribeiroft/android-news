package br.com.robsonribeiroft.androidnews.screen

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import br.com.robsonribeiroft.androidnews.component.toolbar.TopBarComponent
import br.com.robsonribeiroft.androidnews.model.News

@Composable
fun NewsDetailScreen(
    news: News,
    modifier: Modifier = Modifier,
    onBackButtonClicked: ()->Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBarComponent {
            onBackButtonClicked()
        }
        AndroidView(
            modifier = modifier
                .fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            update = { webView ->
                webView.loadUrl(news.url)
            }
        )
    }
}
