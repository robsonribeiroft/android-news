package br.com.robsonribeiroft.androidnews.component.menu


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.extensions.colorTheme
import br.com.robsonribeiroft.androidnews.extensions.empty
import br.com.robsonribeiroft.androidnews.extensions.withColor
import br.com.robsonribeiroft.androidnews.model.MenuItem
import br.com.robsonribeiroft.androidnews.ui.theme.Primary
import br.com.robsonribeiroft.androidnews.ui.theme.SurfaceDark
import br.com.robsonribeiroft.androidnews.ui.theme.SurfaceLight

@Composable
fun MenuItemComponent(
    menu: MenuItem,
    modifier: Modifier = Modifier,
    onClick: (MenuItem)-> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick(menu) }
            .fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(containerColor = colorTheme(SurfaceLight, SurfaceDark)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.small)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(dimensionResource(R.dimen.regular)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = menu.title,
                    style = MaterialTheme.typography.titleLarge.withColor { onSurface }
                )
                Icon(
                    modifier = Modifier.padding(dimensionResource(R.dimen.regular)),
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = String.empty,
                    tint = Primary
                )
            }
        }
    }
}

