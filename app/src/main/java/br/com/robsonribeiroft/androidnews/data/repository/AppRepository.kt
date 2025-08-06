package br.com.robsonribeiroft.androidnews.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.robsonribeiroft.androidnews.data.service.ApiService
import br.com.robsonribeiroft.androidnews.model.News
import kotlinx.coroutines.flow.Flow

class AppRepository(
    private val apiService: ApiService
) {

    fun getPaginatedFeedNews(product: String): Flow<PagingData<News>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(apiService = apiService, product = product)
            }
        ).flow
    }
}