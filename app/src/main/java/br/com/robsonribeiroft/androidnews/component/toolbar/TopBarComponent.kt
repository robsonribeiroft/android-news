package br.com.robsonribeiroft.androidnews.component.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.extensions.colorTheme
import br.com.robsonribeiroft.androidnews.extensions.empty
import br.com.robsonribeiroft.androidnews.ui.theme.Primary
import br.com.robsonribeiroft.androidnews.ui.theme.SurfaceDark

@Composable
fun TopBarComponent(
    modifier: Modifier = Modifier,
    onBackButtonClicked: (()->Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(getTopBarHeight())
            .background(colorTheme(Primary, SurfaceDark)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = dimensionResource(R.dimen.regular),
                alignment = Alignment.Start
            )
        ) {
            if (onBackButtonClicked != null) {
                IconButton(
                    onClick = onBackButtonClicked
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = String.empty,
                        tint = colorResource(R.color.white)
                    )
                }
            }

            Image(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.regular))
                    .size(dimensionResource(R.dimen.extra_large)),
                painter = painterResource(R.drawable.g1_logo),
                contentDescription = String.empty,
                colorFilter = ColorFilter.tint(colorResource(R.color.white))
            )
        }
    }
}

@Composable
fun getTopBarHeight(
    boxExtraHeigh: Dp = dimensionResource(R.dimen.small)
): Dp {
    val density = LocalDensity.current
    return with(density) {
        WindowInsets.statusBars.getTop(density).toDp() +
                dimensionResource(R.dimen.toolbar_heigh) +
                boxExtraHeigh
    }
}
