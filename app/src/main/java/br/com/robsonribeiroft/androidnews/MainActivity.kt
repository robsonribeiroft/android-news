package br.com.robsonribeiroft.androidnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.robsonribeiroft.androidnews.model.News
import br.com.robsonribeiroft.androidnews.navigation.FeedRoute
import br.com.robsonribeiroft.androidnews.navigation.WebViewRoute
import br.com.robsonribeiroft.androidnews.navigation.customArgs
import br.com.robsonribeiroft.androidnews.navigation.map
import br.com.robsonribeiroft.androidnews.screen.FeedScreen
import br.com.robsonribeiroft.androidnews.screen.WebViewScreen
import br.com.robsonribeiroft.androidnews.ui.theme.AndroidNewsTheme
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidNewsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App

                }
            }
        }
    }
}
