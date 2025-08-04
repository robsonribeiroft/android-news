package br.com.robsonribeiroft.androidnews.navigation

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import br.com.robsonribeiroft.androidnews.model.News
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.typeOf

@Serializable
object FeedRoute

@Serializable
data class WebViewRoute(val news: News)


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
