package br.com.robsonribeiroft.androidnews.model

data class News(
    val title: String,
    val summary: String,
    val thumbnailUrl: String,
    val metadata: String,
    val label: String,
    val url: String,
)

val MOCKED_NEWS = News(
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