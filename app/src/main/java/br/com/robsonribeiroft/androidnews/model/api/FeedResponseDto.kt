package br.com.robsonribeiroft.androidnews.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedResponseDto(
    val feed: FeedContainerDto? = null
)