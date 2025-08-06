package br.com.robsonribeiroft.androidnews.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import br.com.robsonribeiroft.androidnews.AppViewModel
import br.com.robsonribeiroft.androidnews.R
import br.com.robsonribeiroft.androidnews.extensions.colorTheme
import br.com.robsonribeiroft.androidnews.navigation.FeedRoute
import br.com.robsonribeiroft.androidnews.navigation.TabScreen
import br.com.robsonribeiroft.androidnews.ui.theme.Primary
import br.com.robsonribeiroft.androidnews.ui.theme.SurfaceDark
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    viewModel: AppViewModel = koinViewModel(),
    navigateToWebView: (url: String) -> Unit
) {
    val tabs = listOf(TabScreen.Home, TabScreen.Agro, TabScreen.Menu)
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabs.size }

    LaunchedEffect(selectedTab) {
        pagerState.animateScrollToPage(selectedTab)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress) {
            selectedTab = pagerState.currentPage
        }
    }

    Column {
        TabRow(
            selectedTabIndex = selectedTab,
            indicator = { tabPositions ->
                if (selectedTab <= tabPositions.size) {
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = colorResource(R.color.white)
                    )
                }
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier.background(colorTheme(Primary, SurfaceDark)),
                    selectedContentColor = colorResource(R.color.white),
                    selected = selectedTab == index,
                    onClick = {
                        if (selectedTab != index) {
                            selectedTab = index
                        }
                    },
                    text = { Text(text = tab.title) }
                )
            }
        }

        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
        ) { index ->
            when(index) {
                0, 1 -> {
                    val route = tabs[index].route as FeedRoute
                    FeedTabContent(product = route.product, viewModel = viewModel, navigateToNews = {navigateToWebView(it.url)})
                }
                else -> MenuTabContent(onItemClick = {navigateToWebView(it.url)})
            }
        }
    }
}