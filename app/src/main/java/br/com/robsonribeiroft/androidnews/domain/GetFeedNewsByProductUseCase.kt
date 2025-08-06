package br.com.robsonribeiroft.androidnews.domain

import androidx.paging.PagingData
import br.com.robsonribeiroft.androidnews.data.repository.AppRepository
import br.com.robsonribeiroft.androidnews.model.News
import kotlinx.coroutines.flow.Flow

class GetFeedNewsByProductPaginatedUseCase(
    private val repository: AppRepository
) {
    operator fun invoke(product: String): Flow<PagingData<News>> {
        return repository.getPaginatedFeedNews(product)
    }
}
