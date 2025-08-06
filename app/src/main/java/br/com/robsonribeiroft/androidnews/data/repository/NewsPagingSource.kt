package br.com.robsonribeiroft.androidnews.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.robsonribeiroft.androidnews.data.service.ApiService
import br.com.robsonribeiroft.androidnews.extensions.empty
import br.com.robsonribeiroft.androidnews.model.News
import br.com.robsonribeiroft.androidnews.model.toNewsList

class NewsPagingSource(
    private val apiService: ApiService,
    private val product: String
) : PagingSource<Int, News>() {
    private var offerId: String? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        val currentPage = params.key ?: 1

        return try {
            val responseDto = if (currentPage == 1) {
                val initialResponse = apiService.getFeed(product)
                offerId = initialResponse.feed?.offer ?:String.empty
                initialResponse
            } else {

                val currentOfferId = offerId ?: throw IllegalStateException("Offer ID was not found after first page")
                apiService.getFeedPage(
                    product = product,
                    id = currentOfferId,
                    page = currentPage
                )
            }

            val newsList = responseDto.toNewsList()
            val nextPageNumber = responseDto.feed?.falkorData?.nextPage

            LoadResult.Page(
                data = newsList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return null
    }
}