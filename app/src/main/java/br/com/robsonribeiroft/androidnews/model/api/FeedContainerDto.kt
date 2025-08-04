package br.com.robsonribeiroft.androidnews.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedContainerDto(
    @SerialName("falkor")
    val falkorData: FalkorDataDto? = null
)

@Serializable
data class FalkorDataDto(
    val items: List<FeedItemDto> = emptyList(),
    val nextPage: Int? = null
)

@Serializable
data class FeedItemDto(
    val id: String,
    val type: String,
    val content: ContentDto? = null,
    val metadata: String? = null
)