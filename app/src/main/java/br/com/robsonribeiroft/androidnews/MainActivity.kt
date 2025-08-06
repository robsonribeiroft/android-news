package br.com.robsonribeiroft.androidnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.robsonribeiroft.androidnews.component.toolbar.TopBarComponent
import br.com.robsonribeiroft.androidnews.navigation.AppNavController
import br.com.robsonribeiroft.androidnews.ui.theme.AndroidNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidNewsTheme {
                AppNavController(Modifier.fillMaxSize())
            }
        }
    }
}
