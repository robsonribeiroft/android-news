package br.com.robsonribeiroft.androidnews.data.service

import br.com.robsonribeiroft.androidnews.model.api.FeedResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("feed/{product}")
    suspend fun getFeed(
        @Path("product") product: String
    ): FeedResponseDto

    @GET("feed/{uri}")
    suspend fun getFeedByUri(
        @Path("uri") uri: String
    ): FeedResponseDto

    @GET("feed/page/{product}/{id}/{page}")
    suspend fun getFeedPage(
        @Path("product") product: String,
        @Path("id") id: String,
        @Path("page") page: Int
    ): FeedResponseDto
}