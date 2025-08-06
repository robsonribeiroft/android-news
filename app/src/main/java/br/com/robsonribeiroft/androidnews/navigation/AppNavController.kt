package br.com.robsonribeiroft.androidnews.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.robsonribeiroft.androidnews.component.toolbar.TopBarComponent
import br.com.robsonribeiroft.androidnews.model.News
import br.com.robsonribeiroft.androidnews.screen.WebViewScreen
import br.com.robsonribeiroft.androidnews.screen.main.MainScreen

@Composable
fun AppNavController(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    var canNavigateBack by remember { mutableStateOf(false) }
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { controller, _, _ ->
            canNavigateBack = controller.previousBackStackEntry != null
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {

        TopBarComponent(
            showBackButton = canNavigateBack) {
            navController.navigateUp()
        }

        NavHost(
            navController = navController,
            startDestination = MainRoute,
            modifier = Modifier.weight(1f)
        ) {
            composable<MainRoute> {
                MainScreen { url ->
                    navController.navigate(WebViewRoute(url))
                }
            }
            composable<WebViewRoute>(typeMap = customArgs<News>().map) { navBackStackEntry ->
                val route = navBackStackEntry.toRoute<WebViewRoute>()
                WebViewScreen(route.url)
            }
        }
    }

}
