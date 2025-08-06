package br.com.robsonribeiroft.androidnews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.robsonribeiroft.androidnews.domain.GetFeedNewsByProductPaginatedUseCase
import br.com.robsonribeiroft.androidnews.extensions.empty
import br.com.robsonribeiroft.androidnews.model.News
import kotlinx.coroutines.flow.Flow

class AppViewModel(
    private val getFeedNewsPagination: GetFeedNewsByProductPaginatedUseCase,
): ViewModel() {

    private var product: String = String.empty

    fun setProduct(product: String) {
        this.product = product
    }

    fun getFeedNewsByProduct(product: String): Flow<PagingData<News>> {
        return getFeedNewsPagination(
            product = product
        ).cachedIn(viewModelScope)
    }
}
