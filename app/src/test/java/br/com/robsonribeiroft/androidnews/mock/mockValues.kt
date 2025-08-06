package br.com.robsonribeiroft.androidnews.mock

import br.com.robsonribeiroft.androidnews.model.TypeNews
import br.com.robsonribeiroft.androidnews.model.api.CategoryLabelDto
import br.com.robsonribeiroft.androidnews.model.api.ContentDto
import br.com.robsonribeiroft.androidnews.model.api.FalkorDataDto
import br.com.robsonribeiroft.androidnews.model.api.FeedContainerDto
import br.com.robsonribeiroft.androidnews.model.api.FeedItemDto
import br.com.robsonribeiroft.androidnews.model.api.FeedResponseDto
import br.com.robsonribeiroft.androidnews.model.api.ImageDto
import br.com.robsonribeiroft.androidnews.model.api.ImageSizesDto
import br.com.robsonribeiroft.androidnews.model.api.ImageUrlDto

val mockFeedResponseDto = FeedResponseDto(
    feed = FeedContainerDto(
        falkorData = FalkorDataDto(
            items = listOf(
                FeedItemDto(
                    id = "dfldnfdlfnd",
                    type = TypeNews.Article.type,
                    metadata = "3 dias",
                    content = ContentDto(
                        title = "title",
                        summary = "summary",
                        recommendationSummary = "recommendationSummary",
                        url = "weburl",
                        categoryLabel = CategoryLabelDto(
                            label = "chapeu"
                        ),
                        image = ImageDto(
                            url = "URL_IMAGEM",
                            sizes = ImageSizesDto(
                                medium = ImageUrlDto(url = "URL_MEDIUM_SIZE"),
                                large = ImageUrlDto(url = "URL_LARGE_SIZE"),
                            )
                        )
                    )
                )
            ),
            nextPage = 2
        ),
        offer = "Offer"
    )
)