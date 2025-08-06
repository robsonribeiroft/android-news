package br.com.robsonribeiroft.androidnews.model

import br.com.robsonribeiroft.androidnews.extensions.empty
import br.com.robsonribeiroft.androidnews.model.api.ContentDto
import br.com.robsonribeiroft.androidnews.model.api.FeedItemDto
import br.com.robsonribeiroft.androidnews.model.api.FeedResponseDto
import kotlinx.serialization.Serializable

@Serializable
data class News(
    val id: String,
    val title: String,
    val summary: String,
    val thumbnailUrl: String,
    val metadata: String,
    val label: String,
    val url: String,
)

val MOCKED_NEWS = News(
    id = "MOCKED_ID",
    title = "Suco de laranja escapa do tarifaço: 'Casados com os americanos, para o bem e para o mal', dizem exportadores",
    summary = "Com lavouras dizimadas, EUA dependem da fruta do Brasil. Sobretaxa para o suco será menor, de 10%.",
    thumbnailUrl = "https://s2-g1.glbimg.com/0XO_iqXqWPS_N9mdAG9pdvSH5w8=/0x0:3500x1969/810x456/smart/https://i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2025/S/H/5aKXEZR2Ol1urHMPF4lA/2025-07-10t150133z-1780113962-rc2qjfaoz5b4-rtrmadp-3-usa-trump-tariffs-brazil-prices.jpg",
    metadata = "Há 3 dias — Em Agronegócios",
    label = "Agronegócios",
    url = "https://g1.globo.com/economia/agronegocios/noticia/2025/07/30/suco-de-laranja-escapa-do-tarifaco-de-50percent-setor-e-dependente-das-vendas-para-os-eua.ghtml"
)

val MOCKED_FEED_NEWS = List(20) { index ->
    MOCKED_NEWS.copy(metadata = "${MOCKED_NEWS.metadata} — $index")
}

enum class TypeNews(val type: String) {
    Article(type = "materia"),
    Basic(type = "basico")
}

fun FeedResponseDto.toNewsList(): List<News> {
    return this.feed
        ?.falkorData
        ?.items
        ?.filter { feedItem -> TypeNews.entries.any { typeNews -> typeNews.type.equals(feedItem.type, true) } }
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
        id = id,
        title = title,
        summary = summary,
        thumbnailUrl = thumbnailUrl,
        metadata = metadata,
        label = label,
        url = url,
    )
}