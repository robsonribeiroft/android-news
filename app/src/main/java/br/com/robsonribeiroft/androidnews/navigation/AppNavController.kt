package br.com.robsonribeiroft.androidnews.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.robsonribeiroft.androidnews.model.News
import br.com.robsonribeiroft.androidnews.screen.FeedScreen
import br.com.robsonribeiroft.androidnews.screen.WebViewScreen


@Composable
fun AppNavController(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FeedRoute,
        modifier = modifier
            .fillMaxSize()
    ) {
        composable<FeedRoute> { navBackStackEntry ->
            FeedScreen { news -> navController.navigate(WebViewRoute(news)) }
        }
        composable<WebViewRoute>(typeMap = customArgs<News>().map) { navBackStackEntry ->
            val route = navBackStackEntry.toRoute<WebViewRoute>()
            WebViewScreen(route.news)
        }
    }
}