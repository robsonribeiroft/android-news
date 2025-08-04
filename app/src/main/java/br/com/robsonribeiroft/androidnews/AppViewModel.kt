package br.com.robsonribeiroft.androidnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.robsonribeiroft.androidnews.domain.GetFeedNewsUseCase
import br.com.robsonribeiroft.androidnews.model.MOCKED_FEED_NEWS
import br.com.robsonribeiroft.androidnews.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel(
    getFeedNewsUseCase: GetFeedNewsUseCase
): ViewModel() {

    private val _feed = MutableStateFlow<List<News>>(emptyList())
    val feed = _feed.asStateFlow()

    init {
        getFeedNewsUseCase(viewModelScope, onError = {}) {
            _feed.value = it
        }
    }

}