package br.com.robsonribeiroft.androidnews.data.repository

import br.com.robsonribeiroft.androidnews.data.service.ApiService
import br.com.robsonribeiroft.androidnews.model.api.FeedResponseDto

class AppRepository(
    private val apiService: ApiService
) {
    suspend fun getFeedNews(): FeedResponseDto = apiService.getFeed("g1")
}