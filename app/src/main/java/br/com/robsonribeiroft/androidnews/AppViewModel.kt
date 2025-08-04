package br.com.robsonribeiroft.androidnews

import androidx.lifecycle.ViewModel
import br.com.robsonribeiroft.androidnews.model.MOCKED_FEED_NEWS
import br.com.robsonribeiroft.androidnews.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel: ViewModel() {

    private val _feed = MutableStateFlow<List<News>>(MOCKED_FEED_NEWS)
    val feed = _feed.asStateFlow()

}