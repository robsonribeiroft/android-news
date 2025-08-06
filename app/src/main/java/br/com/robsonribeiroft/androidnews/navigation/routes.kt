package br.com.robsonribeiroft.androidnews.navigation

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Serializable
data class FeedRoute(val product: String)

@Serializable
data class WebViewRoute(val url: String)

@Serializable
object MainRoute

@Serializable
object MenuRoute

sealed class TabScreen( val title: String, val route: @Serializable Any) {
    data object Home : TabScreen("Destaques", FeedRoute(product = "g1"))
    data object Agro : TabScreen("Agro", FeedRoute(product = "https://g1.globo.com/economia/agronegocios"))
    data object Menu : TabScreen("Menu", MenuRoute)
}


inline fun <reified T> customArgs(): NavType<T> = object : NavType<T>(isNullableAllowed = false) {
    override fun put(bundle: SavedState, key: String, value: T) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun get(bundle: SavedState, key: String): T? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(value)
    }

    override fun serializeAsValue(value: T): String {
        return Uri.encode(Json.encodeToString(value))
    }
}

inline val <reified T> NavType<T>.map get() = mapOf(typeOf<T>() to customArgs<T>())
