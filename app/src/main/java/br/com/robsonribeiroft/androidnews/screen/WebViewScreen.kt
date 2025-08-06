package br.com.robsonribeiroft.androidnews.screen

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.viewinterop.AndroidView
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.component.ErrorComponent

@Composable
fun WebViewScreen(
    newsUrl: String,
    modifier: Modifier = Modifier,
) {

    var isLoading by remember { mutableStateOf(true) }
    var hasError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (hasError) {
            ErrorComponent(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.large))
                    .weight(1f),
                errorMessageId = R.string.error_loading_page,
                icon = Icons.Default.WifiOff
            )
        } else {
            AndroidView(
                modifier = modifier
                    .fillMaxSize(),
                factory = { context ->
                    WebView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        webViewClient = object : WebViewClient() {
                            override fun onPageStarted(
                                view: WebView?,
                                url: String?,
                                favicon: Bitmap?
                            ) {
                                super.onPageStarted(view, url, favicon)
                                isLoading = true
                                hasError = false
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                super.onPageFinished(view, url)
                                isLoading = false
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                super.onReceivedError(view, request, error)
                                hasError = true
                            }
                        }
                    }
                },
                update = { webView ->
                    webView.loadUrl(newsUrl)
                }
            )
        }
        if (isLoading && !hasError) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }
}
