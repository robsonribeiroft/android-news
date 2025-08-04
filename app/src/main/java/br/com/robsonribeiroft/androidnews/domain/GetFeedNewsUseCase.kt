package br.com.robsonribeiroft.androidnews.domain

import br.com.robsonribeiroft.androidnews.data.repository.AppRepository
import br.com.robsonribeiroft.androidnews.extensions.empty
import br.com.robsonribeiroft.androidnews.model.News
import br.com.robsonribeiroft.androidnews.model.api.ContentDto
import br.com.robsonribeiroft.androidnews.model.api.FeedItemDto
import br.com.robsonribeiroft.androidnews.model.api.FeedResponseDto

class GetFeedNewsUseCase(
    private val repository: AppRepository
): UseCase<List<News>, Unit>() {
    override suspend fun run(params: Unit?): List<News> {
        return repository.getFeedNews().toNewsList()
    }
}

fun FeedResponseDto.toNewsList(): List<News> {
    return this.feed
        ?.falkorData
        ?.items
        ?.filter { it.type == "materia" || it.type == "basico" }
        ?.mapNotNull { it.toNews() }
        ?: emptyList()
}

private fun FeedItemDto.toNews(): News? {
    val content: ContentDto = this.content ?: return null

    val title = content.title ?: return null
    val url = content.url ?: return null

    val thumbnailUrl = content.image?.sizes?.medium?.url ?: content.image?.url ?: return null

    val summary = content.recommendationSummary ?: content.summary ?: String.empty
    val label = content.categoryLabel?.label ?: String.empty
    val metadata = this.metadata ?: String.empty

    return News(
        title = title,
        summary = summary,
        thumbnailUrl = thumbnailUrl,
        metadata = metadata,
        label = label,
        url = url,
    )
}