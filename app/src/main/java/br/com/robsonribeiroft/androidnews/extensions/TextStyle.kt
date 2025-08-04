package br.com.robsonribeiroft.androidnews.extensions

import androidx.annotation.ColorRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle


@Composable
fun TextStyle.withColor(@ColorRes colorRes: Int): TextStyle {
    return this.copy(color = colorResource(id = colorRes))
}

@Composable
fun TextStyle.withColor(block: ColorScheme.()-> Color): TextStyle {
    val selectColor = MaterialTheme.colorScheme.block()
    return this.copy(color = selectColor)
}

@Composable
fun colorTheme(light: Color, dark: Color) = if(isSystemInDarkTheme()) dark else light