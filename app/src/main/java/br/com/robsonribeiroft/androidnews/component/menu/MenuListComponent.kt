package br.com.robsonribeiroft.androidnews.component.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.extensions.colorTheme
import br.com.robsonribeiroft.androidnews.model.MenuItem
import br.com.robsonribeiroft.androidnews.ui.theme.BackgroundDark
import br.com.robsonribeiroft.androidnews.ui.theme.BackgroundLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuListComponent(
    modifier: Modifier,
    menuItems: List<MenuItem>,
    onItemClick: (MenuItem)-> Unit
) {

    val listState = rememberLazyListState()

    Box(
        modifier
            .fillMaxSize()
            .background(color = colorTheme(BackgroundLight, BackgroundDark))
    ) {
        LazyColumn(
            modifier = Modifier
                .testTag(MenuListComponentTestTag.TAG)
                .fillMaxSize(),
            state = listState,
            verticalArrangement = Arrangement.spacedBy(
                space = dimensionResource(R.dimen.tiny),
                alignment = Alignment.Top
            )
        ) {
            items(
                menuItems
            ) { menuItem ->
                MenuItemComponent(menuItem, onClick = onItemClick)
            }
        }
    }
}


object MenuListComponentTestTag {
    const val TAG = "MenuListComponentTestTag"
}