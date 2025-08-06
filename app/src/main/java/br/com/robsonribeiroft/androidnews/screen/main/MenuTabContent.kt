package br.com.robsonribeiroft.androidnews.screen.main

import android.content.Context
import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.component.menu.MenuListComponent
import br.com.robsonribeiroft.androidnews.model.MenuItem
import br.com.robsonribeiroft.androidnews.model.MenuResponseDto
import br.com.robsonribeiroft.androidnews.model.toMenuItemsList
import kotlinx.serialization.json.Json

@Composable
fun MenuTabContent(
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem)-> Unit
) {
    MenuListComponent(
        modifier,
        menuItems = parseMenuItemsFromRaw(LocalContext.current, R.raw.menu_items),
        onItemClick = onItemClick
    )
}

fun parseMenuItemsFromRaw(context: Context, @RawRes resId: Int): List<MenuItem> {
    val jsonParser = Json { ignoreUnknownKeys = true }
    return try {
        val inputStream = context.resources.openRawResource(resId)
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        jsonParser.decodeFromString<MenuResponseDto>(jsonString).toMenuItemsList()
    } catch (_: Exception) {
        emptyList()
    }
}