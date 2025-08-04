package br.com.robsonribeiroft.androidnews.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ContentDto(
    val title: String? = null,
    val recommendationSummary: String? = null,
    val summary: String? = null,
    val url: String? = null,
    @SerialName("chapeu")
    val categoryLabel: CategoryLabelDto? = null,
    val image: ImageDto? = null
)

@Serializable
data class CategoryLabelDto(
    val label: String? = null
)

@Serializable
data class ImageDto(
    val url: String? = null,
    val sizes: ImageSizesDto? = null
)

@Serializable
data class ImageSizesDto(
    // Mapeando a imagem de tamanho "M" como exemplo
    @SerialName("M")
    val medium: ImageUrlDto? = null,
    // Mapeando a imagem de tamanho "L"
    @SerialName("L")
    val large: ImageUrlDto? = null
)

@Serializable
data class ImageUrlDto(
    val url: String? = null
)