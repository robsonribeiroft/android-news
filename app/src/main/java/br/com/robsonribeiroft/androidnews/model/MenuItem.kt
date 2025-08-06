package br.com.robsonribeiroft.androidnews.model


import kotlinx.serialization.Serializable

data class MenuItem(
    val title: String,
    val url: String
)

@Serializable
data class MenuResponseDto(
    val menuItems: List<MenuItemDto>
)
@Serializable
data class MenuItemDto(
    val title: String,
    val url: String
)

fun MenuResponseDto?.toMenuItemsList(): List<MenuItem> {
    return this?.menuItems?.map { MenuItem(it.title, it.url) } ?:emptyList()
}